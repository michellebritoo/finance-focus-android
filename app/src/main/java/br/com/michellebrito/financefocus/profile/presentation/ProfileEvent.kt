package br.com.michellebrito.financefocus.profile.presentation

sealed class ProfileEvent {
    data object ShowLoading : ProfileEvent()
    data object HideLoading : ProfileEvent()
    data object ShowError: ProfileEvent()
    data class ShowUserInfo(
        val name: String,
        val email: String,
        val goals: String,
        val rates: String
    ) : ProfileEvent()
}
