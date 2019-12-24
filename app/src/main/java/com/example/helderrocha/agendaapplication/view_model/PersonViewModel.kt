package com.example.helderrocha.agendaapplication.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.helderrocha.agendaapplication.SchedulerProvider
import com.example.helderrocha.agendaapplication.api.ApiClient
import com.example.helderrocha.agendaapplication.model.PersonModel
import com.example.helderrocha.agendaapplication.model.PersonObject
import javax.inject.Inject

class PersonViewModel @Inject constructor(val api: ApiClient, private val schedulers: SchedulerProvider) : ViewModel() {
    val _data = MutableLiveData<PersonModel>()
    val data: LiveData<PersonModel> = _data

    fun getPeopleId(id: Int) {

        api.peopleId(id)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe({
                    _data.value = it.data
                }, {
                    Log.i("ERROR", it.message)
                })
    }
}


