package com.example.helderrocha.agendaapplication.model

data class OrganizationModel(
        var id: Int,
        var name: String,
        var email: String,
        var cnpj: String,
        var description: String,
        var logo: Logo,
        var updatedAt: String,
        var ranking: Int,
        var website: String,
        var createdAt: String,
        var contact: Contact,
        var address: Address,
        var category: Category,
        var leadOrigin: LeadOrigin,
        var sector: Sector

)