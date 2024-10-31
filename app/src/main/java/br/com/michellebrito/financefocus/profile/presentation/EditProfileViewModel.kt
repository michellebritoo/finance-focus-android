package br.com.michellebrito.financefocus.profile.presentation

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.profile.domain.EditUserDetailsModel
import br.com.michellebrito.financefocus.profile.domain.ProfileRepository
import br.com.michellebrito.financefocus.util.extensions.isEmptyOrBlank
import kotlinx.coroutines.launch

class EditProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _viewState = MutableLiveData<EditProfileEvent>()
    val viewState: LiveData<EditProfileEvent> = _viewState

    fun onContinueClicked(name: String?, email: String?) {
        sendUIEvent(EditProfileEvent.ShowLoading)

        viewModelScope.launch {
            repository.onEditProfile(
                model = EditUserDetailsModel(
                    if (name.isEmptyOrBlank()) null else name,
                    if (email.isEmptyOrBlank()) null else email
                ),
                onSuccess = { sendUIEvent(EditProfileEvent.OnSuccess) },
                onError = { sendUIEvent(EditProfileEvent.ShowError) }
            )
            sendUIEvent(EditProfileEvent.HideLoading)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    }

    private fun sendUIEvent(event: EditProfileEvent) {
        _viewState.value = event
    }
}
