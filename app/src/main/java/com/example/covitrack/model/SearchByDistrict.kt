package com.example.covitrack.model

import com.google.gson.annotations.SerializedName

data class SearchByDistrict(

	@field:SerializedName("districts")
	val districts: List<DistrictsItem?>? = null,

	@field:SerializedName("ttl")
	val ttl: Int? = null
)

data class DistrictsItem(

	@field:SerializedName("district_name")
	val districtName: String? = null,

	@field:SerializedName("district_id")
	val districtId: Int? = null
)
