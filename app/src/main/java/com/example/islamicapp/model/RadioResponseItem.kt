package com.example.islamy_project.api.model

import com.google.gson.annotations.SerializedName

data class RadioResponseItem(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("url")
    val radioUrl: String? = null
)