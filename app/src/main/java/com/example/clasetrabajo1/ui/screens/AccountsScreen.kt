package com.example.clasetrabajo1.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clasetrabajo1.data.ViewModel.AccountViewModel
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.clasetrabajo1.data.model.AccountModel
import com.example.clasetrabajo1.ui.components.AccountCardComponent
import com.example.clasetrabajo1.ui.components.AccountDetailCardComponent
import com.example.clasetrabajo1.ui.components.TopBarComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen(
    navController: NavHostController,
    viewModel: AccountViewModel = viewModel()
) {
    var accounts by remember { mutableStateOf<List<AccountModel>>(emptyList()) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    var accountDetail by remember { mutableStateOf<AccountModel?>(null) }
    Column() {
        TopBarComponent("Accounts", navController, "Accounts Screen")
        LaunchedEffect(Unit) {
            viewModel.getAccounts { response ->
                if(response.isSuccessful){
                    accounts = response.body() ?: emptyList()
                } else {
                    Log.d("debug", "Failed to load data")
                }
            }
        }
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = listState
        ){
            items(accounts){ account ->
                AccountCardComponent(
                    account.id,
                    account.name,
                    account.userName,
                    account.imagenURL,
                    onButtonClick = {
                        viewModel.getAccount(account.id){ response ->
                            if(response.isSuccessful){
                                accountDetail = response.body()
                            }
                        }
                        showBottomSheet = true
                    }
                )
            }
        }
    }

    if(showBottomSheet){
        ModalBottomSheet(
            modifier = Modifier
                .fillMaxHeight(),
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            AccountDetailCardComponent(
                accountDetail?.id ?: 0,
                accountDetail?.name ?: "",
                accountDetail?.userName ?: "",
                accountDetail?.password ?: "",
                accountDetail?.imagenURL ?: "",
                accountDetail?.descripcion ?: ""
            )
        }
    }
}