package com.example.helderrocha.agendaapplication.model

import com.google.gson.annotations.SerializedName

data class PeopleList (
        @SerializedName("data") val data: List<PersonModel>
)