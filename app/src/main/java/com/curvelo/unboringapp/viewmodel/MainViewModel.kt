package com.curvelo.unboringapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.curvelo.unboringapp.data.ApiClient
import com.curvelo.unboringapp.model.Bored
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    val activityData: MutableLiveData<String> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getRandomActivity() {
        val call = ApiClient.apiService.apiRandomActivity()
        call.enqueue(object : Callback<Bored> {
            override fun onResponse(call: Call<Bored>, response: Response<Bored>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        activityData.value = "Activity: ${it.activity}\n\nType: ${it.type}\n\n"
                    }
                } else {
                    errorMessage.value = "Ops! Ocorreu um erro!"
                }
            }

            override fun onFailure(call: Call<Bored>, t: Throwable) {
                errorMessage.value = "Tente Novamente."
            }
        })
    }
}
