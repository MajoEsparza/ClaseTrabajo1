package com.example.clasetrabajo1.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(title: String, navController: NavController, location:String ){
    TopAppBar(
        title = { Text(title) },
        actions = {
            if (location == "accountsScreen") {
                IconButton(onClick = { navController.navigate("manageAcScreen") }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Search")
                }
            }
        }
    )
}