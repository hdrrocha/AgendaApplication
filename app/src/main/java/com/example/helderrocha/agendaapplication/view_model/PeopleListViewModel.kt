package com.example.helderrocha.agendaapplication.view_model


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.helderrocha.agendaapplication.SchedulerProvider
import com.example.helderrocha.agendaapplication.api.ApiClient
import com.example.helderrocha.agendaapplication.model.OrganizationModel
import com.example.helderrocha.agendaapplication.model.PeopleList
import com.example.helderrocha.agendaapplication.model.PersonModel
import javax.inject.Inject

class PeopleListViewModel @Inject constructor(val api: ApiClient, private val schedulers: SchedulerProvider) : ViewModel() {
    val _data = MutableLiveData<List<PersonModel>>()
    val data: LiveData<List<PersonModel>> = _data

    fun getPeople(page: Int) {

        api.people(page)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe({
                    _data.value = it.data
                    Log.i("HELDER ====>", it.data.get(1).name)
                }, {
                    Log.i("ERROR", it.message)
                })
    }
}



