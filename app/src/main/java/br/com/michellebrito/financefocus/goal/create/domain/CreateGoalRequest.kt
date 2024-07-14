package br.com.michellebrito.financefocus.goal.create.domain

data class CreateGoalRequest(
    val name: String,
    val description: String,
    val totalValue: Float,
    val gradualProgress: Boolean,
    val monthFrequency: Boolean,
    val initDate: String,
    val finishDate: String
)
