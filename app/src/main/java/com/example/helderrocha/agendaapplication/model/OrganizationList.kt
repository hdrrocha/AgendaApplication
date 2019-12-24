package com.example.helderrocha.agendaapplication.model
import com.google.gson.annotations.SerializedName

data class OrganizationList (
        @SerializedName("data") val data: List<OrganizationModel>
)