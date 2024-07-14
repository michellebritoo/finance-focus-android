package br.com.michellebrito.financefocus.goal.increment.domain

data class ExpectedDepositResponse(
    val id: String,
    val depositId: String,
    val value: Float,
    val completed: Boolean
)
