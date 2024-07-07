package br.com.michellebrito.financefocus.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.profile.domain.ProfileRepository
import kotlinx.coroutines.launch

class ProfileFragmentViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _viewState = MutableLiveData<ProfileEvent>()
    val viewState: LiveData<ProfileEvent> = _viewState

    fun getUserDetails() {
        sendUIEvent(ProfileEvent.ShowLoading)
        viewModelScope.launch {
            val response = repository.getUserDetails()
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
        }
    }

    private fun sendUIEvent(event: ProfileEvent) {
        _viewState.value = event
    }
}
