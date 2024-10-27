package br.com.michellebrito.financefocus.goal.details.presentation

sealed class GoalDetailEvent {
    data object ShowLoading: GoalDetailEvent()
    data object HideLoading: GoalDetailEvent()
    data object ShowError: GoalDetailEvent()
    data object OnSuccessDelete: GoalDetailEvent()
    data object HasCompletedGoal: GoalDetailEvent()
    data class ShowGoal(
        val name: String,
        val description: String,
        val value: Double,
        val remainingValue: Double,
        val progress: Float,
        val date: String,
        val gradualProgress: Boolean,
        val monthFrequency: Boolean
    ): GoalDetailEvent()
    data class GoToIncrementScreen(val id: String, val completed: Boolean): GoalDetailEvent()
}
