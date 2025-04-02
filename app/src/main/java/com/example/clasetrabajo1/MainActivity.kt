package com.example.clasetrabajo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clasetrabajo1.ui.screens.ComponentsScreen
import com.example.clasetrabajo1.ui.screens.HomeScreen
import com.example.clasetrabajo1.ui.screens.InterfaceScreen
import com.example.clasetrabajo1.ui.screens.MainMenuScreen
import com.example.clasetrabajo1.ui.screens.TestScreen
import com.example.clasetrabajo1.ui.theme.ClaseTrabajoTheme

class MainActivity : ComponentActivity() {
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
fun ComposeMultiScreenApp(){
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
        composable("componentsScreen"){ ComponentsScreen(navController) }
    }

}