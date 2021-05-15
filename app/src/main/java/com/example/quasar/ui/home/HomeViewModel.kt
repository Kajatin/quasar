package com.example.quasar.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quasar.api.NasaService
import com.example.quasar.data.NasaPicture
import java.util.*

class HomeViewModel : ViewModel() {
    private val apod: MutableLiveData<NasaPicture> by lazy {
        MutableLiveData<NasaPicture>().also {
            fetchApod(it)
        }
    }

    private val service: NasaService = NasaService.create()

    fun getApod(): LiveData<NasaPicture> {
        return apod;
    }

    private fun fetchApod(_apod: MutableLiveData<NasaPicture>) {
        val cal = Calendar.getInstance()
        val date = cal.get(Calendar.YEAR).toString()+"-"+(cal.get(Calendar.MONTH)+1).toString()+"-"+cal.get(Calendar.DAY_OF_MONTH).toString()

        com.example.quasar.api.fetchApod(
            service,
            date,
            {
                val id = it.date.replace("-","").toInt()
                _apod.apply {
                    value = NasaPicture(id,it.title,it.explanation,it.url)
                }
//                Sentry.captureMessage(_apod.toString())
                Log.d("HomeViewModel", "Fetched APOD: " + id.toString())
            },
            {
                _apod.apply {
                    value = NasaPicture(-1)
                }
                Log.d("HomeViewModel", "Error fetching APOD: " + it) //TODO: Handle errors (example response {"code":400,"msg":"time data '2019-03-' does not match format '%Y-%m-%d'","service_version":"v1"})
            }
        )
    }
}