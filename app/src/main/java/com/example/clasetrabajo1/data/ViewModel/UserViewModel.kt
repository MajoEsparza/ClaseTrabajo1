package com.example.clasetrabajo1.data.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clasetrabajo1.data.Network.RetrofitClient
import com.example.clasetrabajo1.data.model.UserModel
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class UserViewModel:ViewModel(){
    val api = RetrofitClient.api

    fun loginApi(user_model: UserModel, onResult: (JsonObject?) -> Unit){
        viewModelScope.launch {
            try {
                val response = api.login(user_model)
                if(response.isSuccessful){
                    val jsonResponse = response.body()
                    Log.d("debug", response.body().toString())
                    onResult(jsonResponse as JsonObject?)
                } else {
                    Log.d("debug", "Error: ${response.body()}")
                    onResult(null)
                }
            } catch (exception: Exception){
                Log.d("debug", "API CALL FAILED: $exception")
                onResult(null)
            }
        }
    }
}