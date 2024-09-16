package ru.blackmirrror.rxjavaexamples.data.models

import com.google.gson.annotations.SerializedName

data class CountryDTO (

    @SerializedName("country" ) var country : String

)