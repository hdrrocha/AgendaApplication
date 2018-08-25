package com.example.helderrocha.agendaapplication.model

data class Address(
        var country: String,
        var district: String,
        var streetName: String,
        var streetNumber: Int,
        var additionalInfo: String,
        var postalCode: String,
        var state: String,
        var city: String
)