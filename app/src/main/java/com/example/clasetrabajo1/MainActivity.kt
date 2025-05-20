package com.example.clasetrabajo1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.clasetrabajo1.ui.screens.AccountsScreen
import com.example.clasetrabajo1.ui.screens.BiometricScreen
import com.example.clasetrabajo1.ui.screens.Calendar
import com.example.clasetrabajo1.ui.screens.Camera
import com.example.clasetrabajo1.ui.screens.ComponentsScreen
import com.example.clasetrabajo1.ui.screens.FavoriteAccountsScreen
import com.example.clasetrabajo1.ui.screens.HomeScreen
import com.example.clasetrabajo1.ui.screens.InterfaceScreen
import com.example.clasetrabajo1.ui.screens.LoginScreen
import com.example.clasetrabajo1.ui.screens.MainMenuScreen
import com.example.clasetrabajo1.ui.screens.ManageAccountScreen
import com.example.clasetrabajo1.ui.screens.TestScreen
import com.example.clasetrabajo1.ui.theme.ClaseTrabajoTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClaseTrabajoTheme {
                ComposeMultiScreenApp()
            }
        }
    }
}

@Composable
fun ComposeMultiScreenApp()
{
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "mainMenu") {
//add route name for every screen
        composable("mainMenu") { MainMenuScreen(navController) }
        composable("homeScreen") { HomeScreen(navController) }
        composable("testScreen") { TestScreen(navController) }
        composable("interfaceScreen") { InterfaceScreen(navController) }
        composable("componentsScreen") { ComponentsScreen(navController) }
        composable("loginScreen") { LoginScreen(navController) }
        composable("accoutsScreen") { AccountsScreen(navController) }
        composable("manage_account_screen") { ManageAccountScreen(navController) }
        composable("Camara_Screen") { Camera(navController) }
        composable(
            route = "manage_account_screen/{id}",
            arguments = listOf(navArgument("id") { defaultValue = -1 })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            ManageAccountScreen(
                navController = navController,
                accountId = id
            )
        }
        composable("favoritesScreen") { FavoriteAccountsScreen(navController) }
        composable("Calendar_Screen") { Calendar(navController) }
        composable("biometric_screen") {
            val context = LocalContext.current
            BiometricScreen(navController, onAuthSuccess = {
                Toast.makeText(context, "¡Autenticación exitosa!", Toast.LENGTH_SHORT).show()
            })
        }

    }

    }

