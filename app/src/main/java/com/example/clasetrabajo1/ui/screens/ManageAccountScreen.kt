package com.example.clasetrabajo1.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.clasetrabajo1.data.ViewModel.AccountViewModel
import com.example.clasetrabajo1.data.model.AccountModel
import com.example.clasetrabajo1.ui.components.TopBarComponent


@Composable
fun ManageAccountScreen(
    navController: NavController,
    accountId: Int? = null,
    viewModel: AccountViewModel = viewModel()
){
    val account = remember { mutableStateOf(AccountModel()) }
    val context = LocalContext.current

    LaunchedEffect(accountId) {
        accountId?.let {
            viewModel.getAccount(it){ response ->
                if(response.isSuccessful){
                    account.value = response.body() ?: AccountModel()
                } else{
                    Toast.makeText(
                        context,
                        "Error loading account",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
            .fillMaxSize()
    ){
        TopBarComponent("Add or Edit account", navController, "manage_account_screen")

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.name,
            maxLines = 1,
            label = {Text("Account Name")},
            onValueChange = {account.value = account.value.copy(name = it)}
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.username,
            maxLines = 1,
            label = {Text("Account User Name")},
            onValueChange = {account.value = account.value.copy(username = it)}
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.password,
            maxLines = 1,
            label = {Text("Account Password")},
            onValueChange = {account.value = account.value.copy(password = it)}
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.description,
            maxLines = 1,
            label = {Text("Account Description")},
            onValueChange = {account.value = account.value.copy(description = it)}
        )

        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp),
            onClick = {
                TryCreateAccount(account, context, viewModel, accountId, navController)
            }
        ) {
            Text(text = "Save Account")
        }

        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp),
            onClick = {
                TryDeleteAccount(accountId, context, viewModel, navController)
            }
        ) {
            Text(text = "Delete Account")
        }
    }
}

fun TryCreateAccount(
    accountState: MutableState<AccountModel>,
    context: Context,
    viewModel: AccountViewModel,
    accountId: Int?,
    navController: NavController
){
    val accountS = accountState.value

    if(
        accountS.name.isEmpty() ||
        accountS.username.isEmpty() ||
        accountS.password.isEmpty() ||
        accountS.description.isEmpty()
    ) {
        Toast.makeText(
            context,
            "None of the fields can be empty",
            Toast.LENGTH_SHORT
        ).show()
        return
    } else{
        viewModel.createAccount(accountS){ jsonResponse ->
            val createAccStatus = jsonResponse?.get("store")?.asString
            if(accountId == null){
                viewModel.getAccounts { jsonResponse ->
                    Log.d("debug", "Create account status: $createAccStatus")
                    if(createAccStatus == "success"){
                        Toast.makeText(
                            context,
                            "Account created successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                    } else{
                        Toast.makeText(
                            context,
                            "Error creating account",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else{
                viewModel.updateAccount(accountId,accountS) { jsonResponse ->
                    val updateAccStatus = jsonResponse?.get("update")?.asString
                    if(updateAccStatus == "success"){
                        Toast.makeText(
                            context,
                            "Account updated successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                    } else{
                        Toast.makeText(
                            context,
                            "Error updating account",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}

fun TryDeleteAccount(
    accountId: Int?,
    context: Context,
    viewModel: AccountViewModel,
    navController: NavController
) {
    if (accountId == null) {
        Toast.makeText(context, "No account to delete", Toast.LENGTH_SHORT).show()
        return
    }

    viewModel.deleteAccount(accountId) { jsonResponse ->
        val deleteStatus = jsonResponse?.get("delete")?.asString
        Log.d("debug", "Delete account status: $deleteStatus")

        if (deleteStatus == "success") {
            Toast.makeText(
                context,
                "Account deleted successfully",
                Toast.LENGTH_SHORT
            ).show()
            navController.popBackStack() // Regresa a la pantalla anterior
        } else {
            Toast.makeText(
                context,
                "Error deleting account",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}