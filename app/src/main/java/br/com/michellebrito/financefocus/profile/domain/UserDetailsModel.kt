package br.com.michellebrito.financefocus.profile.domain

data class UserDetailsModel(
    val name: String,
    val email: String,
    val completedGoals: Int
)
