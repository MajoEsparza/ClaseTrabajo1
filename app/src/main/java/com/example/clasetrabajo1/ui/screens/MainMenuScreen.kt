package com.example.clasetrabajo1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable

fun MainMenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Text("Main Menu")
        Button(
            onClick = { navController.navigate("homeScreen") }
        )
        {
            Text("Go to Home Screen")
        }
        Button(
            onClick = { navController.navigate("testscreen") }
        )
        {
            Text("Go to Test Screen")
        }
        Button(
            onClick = { navController.navigate("InterfaceScreen") }
        )
        {
            Text("Go to Interface Screen")
        }
        Button(
            onClick = { navController.navigate("ComponentsScreen") }
        )
        {
            Text("Go to Components Screen")
        }
        Button(
            onClick = { navController.navigate("loginScreen") }
        )
        {
            Text("Go to Login Screen")
        }
        Button(
            onClick = { navController.navigate("accoutsScreen") }
        ) {
            Text("Go to Accounts Screen")
        }
        Button(
            onClick = { navController.navigate("manage_account_screen") }
        ) {
            Text("Go to Manage Account Screen")
        }
        Button(
            onClick = {navController.navigate("favoritesScreen")}
        ) {
            Text("Go to Favorite Account Screen")
        }
    }
}