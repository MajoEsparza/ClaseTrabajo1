package com.example.clasetrabajo1.data.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clasetrabajo1.data.Network.RetrofitClient
import com.example.clasetrabajo1.data.model.AccountModel
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Response

class AccountViewModel:ViewModel(){
    val api = RetrofitClient.api

    fun getAccounts(onResult: (Response<List<AccountModel>>) -> Unit){
        viewModelScope.launch {
            try{
                val response = api.getAccounts()
                Log.d("debug", response.toString())
                onResult(response)
            } catch (exception: Exception){
                Log.d("debug", "API ERROR: $exception")
            }
        }
    }

    fun getAccount(id: Int, onResult: (Response<AccountModel>) -> Unit){
        viewModelScope.launch {
            try{
                val response = api.getAccount(id)
                Log.d("debug", response.toString())
            } catch(exception: Exception){
                Log.d("debug", "API ERROR: $exception")
            }
        }
    }

    fun createAccount(service: AccountModel, onResult: (Response:JsonObject?) -> Unit){
        viewModelScope.launch{
            try {
                val response = api.addAccount(service)
                if(response.isSuccessful){
                    val jsonResponse = response.body()
                    Log.d("debug", "${response.body()}")
                    onResult(jsonResponse as JsonObject?)
                }else{
                    Log.d("debug", "ERROR: ${response.body()}")
                    onResult(null)
                }
            }catch (exception: Exception){
                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)
            }
        }
    }

}
