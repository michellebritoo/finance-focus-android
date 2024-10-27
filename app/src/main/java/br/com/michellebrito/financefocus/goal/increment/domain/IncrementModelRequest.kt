package br.com.michellebrito.financefocus.goal.increment.domain

data class IncrementModelRequest(
    val goalId: String,
    val expectedDepositId: String,
    val valueToIncrement: Float
)
