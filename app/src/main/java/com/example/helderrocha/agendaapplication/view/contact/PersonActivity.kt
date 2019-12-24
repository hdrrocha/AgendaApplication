package com.example.helderrocha.agendaapplication.view.contact

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.helderrocha.agendaapplication.R
import com.example.helderrocha.agendaapplication.model.*
import com.example.helderrocha.agendaapplication.view_model.PersonViewModel
import com.example.helderrocha.agendaapplication.view_model.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.person_activity.*
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

        val Item = intent.extras!!.getString("id")
        var idUser: Int
        idUser = Item.toInt()

        if(idUser != null){
            organizationsViewModel.data.observe(this, ItemsObserver)
            organizationsViewModel.getPeopleId(idUser)
        }

        backButton.setOnClickListener(View.OnClickListener {
            finish()
        })

    }

    private fun onItemsFetched(person: PersonModel?) {
        if (person != null) {
            textViewNameTitle.text = person.name
            textRoleTitle.text = person.role
            textOrganizationTitle.text = person.organization.name
            textNome.text =  person.name
            textCpf.text = person.cpf.toString()
            textAniversaio.text = person.birthday.toString()
            textEndereco.text = "rua ${person.address.streetName}, ${person.address.streetNumber}, ${person.address.postalCode},  ${person.address.district},  ${person.address.city},  ${person.address.state},  ${person.address.country}"
            textPeronCategoria.text = person.category.name
            textEmail.text = person.email
            textTelefone.text = person.contact.work
            textCelular.text = person.contact.whatsapp
            Glide.with(this)
                    .load(person.avatar)
                    .into(profile_image)

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
