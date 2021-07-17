package com.example.covitrack.model

import com.google.gson.annotations.SerializedName

data class SearchByPinCode(

	@field:SerializedName("sessions")
	val sessions: List<SessionsItem?>? = null
)

data class SessionsItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("pincode")
	val pincode: Int? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("min_age_limit")
	val minAgeLimit: Int? = null,

	@field:SerializedName("fee")
	val fee: String? = null,

	@field:SerializedName("session_id")
	val sessionId: String? = null,

	@field:SerializedName("fee_type")
	val feeType: String? = null,

	@field:SerializedName("available_capacity")
	val availableCapacity: Int? = null,

	@field:SerializedName("long")
	val jsonMemberLong: Int? = null,

	@field:SerializedName("district_name")
	val districtName: String? = null,

	@field:SerializedName("block_name")
	val blockName: String? = null,

	@field:SerializedName("vaccine")
	val vaccine: String? = null,

	@field:SerializedName("slots")
	val slots: List<String?>? = null,

	@field:SerializedName("center_id")
	val centerId: Int? = null,

	@field:SerializedName("state_name")
	val stateName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("from")
	val from: String? = null,

	@field:SerializedName("to")
	val to: String? = null,

	@field:SerializedName("allow_all_age")
	val allowAllAge: Boolean? = null,

	@field:SerializedName("available_capacity_dose2")
	val availableCapacityDose2: Int? = null,

	@field:SerializedName("lat")
	val lat: Int? = null,

	@field:SerializedName("available_capacity_dose1")
	val availableCapacityDose1: Int? = null
)
