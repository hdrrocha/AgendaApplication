package com.example.helderrocha.agendaapplication.model

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName


data class PersonModel (
        var id: Int,
        var name: String,
        var email: String,
        var organization: OrganizationModel,
        var cpf: String,
        var role: String,
        var ranking: Int,
        var description: String,
        var birthday: String,
        var avatar: String,
        var createdAt: String,
        var updatedAt: String,
        var contact: Contact,
        var address: Address,
        var category: Category
        )