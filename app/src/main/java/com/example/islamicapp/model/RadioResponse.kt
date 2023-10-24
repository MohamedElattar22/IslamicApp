package com.example.islamy_project.api.model

import com.google.gson.annotations.SerializedName


class RadioResponse (
    @field:SerializedName("radios")
    val radios: List<RadioResponseItem>?=null
)
