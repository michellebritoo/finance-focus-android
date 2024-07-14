package br.com.michellebrito.financefocus.goal.list.domain

data class GoalResponse(
    val id: String,
    val userUID: String,
    val depositId: String,
    val name: String,
    val description: String,
    val totalValue: Double,
    val remainingValue: Double,
    val gradualProgress: Boolean,
    val monthFrequency: Boolean,
    val initDate: String,
    val finishDate: String,
    val concluded: Boolean
)
