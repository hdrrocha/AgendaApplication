package com.example.helderrocha.agendaapplication.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.helderrocha.agendaapplication.R
import com.example.helderrocha.agendaapplication.model.OrganizationModel
import com.example.helderrocha.agendaapplication.view_model.OrganizationViewModel
import com.example.helderrocha.agendaapplication.view_model.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.organization_activity.*
import kotlinx.android.synthetic.main.person_activity.*
import javax.inject.Inject

class OrganizationActivity : AppCompatActivity() {
    @Inject
    lateinit var organizationsVMFactory: ViewModelFactory<OrganizationViewModel>

    private val organizationsViewModel by lazy {
        ViewModelProviders.of(this, organizationsVMFactory)[OrganizationViewModel::class.java]
    }
    protected val ItemsObserver = Observer<OrganizationModel>(::onItemsFetched)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.organization_activity)


        val Item = intent.extras!!.getString("id")
        var idOrganization: Int
        idOrganization = Item.toInt()

        if(idOrganization != null){
            organizationsViewModel.data.observe(this, ItemsObserver)
            organizationsViewModel.getOrganizationId(idOrganization)
        }
    }

    private fun onItemsFetched(organization: OrganizationModel?) {
        if (organization != null) {
            textViewOrganizationTitle.text = organization.name
            textSectorTitle.text = organization.sector.name
            textOrganizationWebSite.text = organization.website
            textOrganizationNome.text =  organization.name
            textCnpj.text = organization.cnpj.toString()
            textOrganizationEndereco.text = "rua ${organization.address.streetName}, ${organization.address.streetNumber}, ${organization.address.postalCode},  ${organization.address.district},  ${organization.address.city},  ${organization.address.state},  ${organization.address.country}"
            textTitleOrganizationCategoria.text = organization.category.name
            textOrganizationEmail.text = organization.email
            textOrgaizationTelefone.text = organization.contact.work
            textOrganizationCelular.text = organization.contact.whatsapp
            Glide.with(this)
                    .load(organization.logo.thumb)
                    .into(organization_image)

        }
    }
}
