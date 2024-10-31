package br.com.michellebrito.financefocus.profile.presentation

sealed class EditProfileEvent {
    data object ShowLoading : EditProfileEvent()
    data object HideLoading : EditProfileEvent()
    data object ShowError: EditProfileEvent()
    data object OnSuccess: EditProfileEvent()
}
