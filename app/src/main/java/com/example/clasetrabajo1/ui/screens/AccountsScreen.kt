package com.example.clasetrabajo1.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.navigation.NavController
import com.example.clasetrabajo1.ui.components.TopBarComponent


@Composable
fun AccountsScreen(navController: NavController){
    Column(){
        //Text("Account Screen")
        TopBarComponent("Accounts")
    }
}