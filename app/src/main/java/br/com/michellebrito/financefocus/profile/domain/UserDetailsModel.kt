package br.com.michellebrito.financefocus.profile.domain

data class UserDetailsModel(
    val name: String,
    val email: String,
    val concludedGoals: Int,
    val rateSimulation: Int
)
