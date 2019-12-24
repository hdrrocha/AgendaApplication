package com.example.helderrocha.agendaapplication.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.helderrocha.agendaapplication.SchedulerProvider
import com.example.helderrocha.agendaapplication.api.ApiClient
import com.example.helderrocha.agendaapplication.model.OrganizationModel
import javax.inject.Inject

class OrganizationViewModel @Inject constructor(val api: ApiClient, private val schedulers: SchedulerProvider) : ViewModel() {
    val _data = MutableLiveData<OrganizationModel>()
    val data: LiveData<OrganizationModel> = _data

    fun getOrganizationId(id: Int) {

        api.organizationId(id)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe({
                    _data.value = it.data
                }, {
                    Log.i("ERROR", it.message)
                })
    }
}


