package br.com.michellebrito.financefocus.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.profile.domain.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragmentViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _viewState = MutableLiveData<ProfileEvent>()
    val viewState: LiveData<ProfileEvent> = _viewState

    fun getUserDetails() {
        sendUIEvent(ProfileEvent.ShowLoading)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getUserDetails()
                }
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        sendUIEvent(
                            ProfileEvent.ShowUserInfo(
                                body.name,
                                body.email,
                                body.concludedGoals.toString(),
                                body.rateSimulation.toString()
                            )
                        )
                    }
                } else {
                    sendUIEvent(ProfileEvent.ShowError)
                }
                sendUIEvent(ProfileEvent.HideLoading)
            } catch (e: Exception) {
                sendUIEvent(ProfileEvent.HideLoading)
                sendUIEvent(ProfileEvent.ShowError)
            }
        }
    }

    fun onDeleteAccountClicked() {
        sendUIEvent(ProfileEvent.ShowLoading)
        viewModelScope.launch {
            repository.deleteUser(
                onSuccess = { sendUIEvent(ProfileEvent.Logout) },
                onError = { sendUIEvent(ProfileEvent.ShowError) }
            )
            sendUIEvent(ProfileEvent.HideLoading)
        }
    }

    fun onLogoutClicked() {
        sendUIEvent(ProfileEvent.ShowLoading)
        viewModelScope.launch {
            repository.logout(
                onSuccess = { sendUIEvent(ProfileEvent.Logout) },
                onError = { sendUIEvent(ProfileEvent.ShowError) }
            )
            sendUIEvent(ProfileEvent.HideLoading)
        }
    }

    private fun sendUIEvent(event: ProfileEvent) {
        _viewState.value = event
    }
}
