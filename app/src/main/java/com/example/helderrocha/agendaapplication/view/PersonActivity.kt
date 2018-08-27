package com.example.helderrocha.agendaapplication.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.helderrocha.agendaapplication.R
import com.example.helderrocha.agendaapplication.model.PersonModel
import com.example.helderrocha.agendaapplication.view_model.PeopleListViewModel
import com.example.helderrocha.agendaapplication.view_model.PersonViewModel
import com.example.helderrocha.agendaapplication.view_model.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class PersonActivity : AppCompatActivity() {
    @Inject
    lateinit var organizationsVMFactory: ViewModelFactory<PersonViewModel>

    private val organizationsViewModel by lazy {
        ViewModelProviders.of(this, organizationsVMFactory)[PersonViewModel::class.java]
    }

    protected val ItemsObserver = Observer<PersonModel>(::onItemsFetched)
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_activity)

//        val idExtra = intent.getStringExtra("id")
        val Item = intent.extras!!.getString("id")
        var idUser: Int
        idUser = Item.toInt()

        organizationsViewModel.data.observe(this, ItemsObserver)
        organizationsViewModel.getPeopleId(idUser)
    }

    private fun onItemsFetched(person: PersonModel?) {
        if (person != null) {
           Log.i("Nome", person.name)
        }
    }
}
