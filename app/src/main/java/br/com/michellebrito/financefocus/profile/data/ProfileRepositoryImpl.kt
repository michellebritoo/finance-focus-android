package br.com.michellebrito.financefocus.profile.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.profile.domain.ProfileRepository
import br.com.michellebrito.financefocus.profile.domain.UserDetailsModel
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Response

class ProfileRepositoryImpl(
    private val client: ProfileClient,
    private val preferenceStorage: PreferenceStorage
): ProfileRepository {

    override suspend fun getUserDetails(): Response<UserDetailsModel> {
        return client.getUserDetails(
            preferenceStorage.getString(AUTHORIZATION)
        )
    }

    override suspend fun deleteUser(onSuccess: () -> Unit, onError: () -> Unit) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        currentUser?.let { user ->
            user.delete().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    preferenceStorage.clearStorage()
                    firebaseAuth.signOut()
                    onSuccess()
                } else {
                    onError()
                }
            }
        }
    }

    override suspend fun logout(onSuccess: () -> Unit, onError: () -> Unit) {
        try {
            preferenceStorage.clearStorage()
            FirebaseAuth.getInstance().signOut()
            onSuccess()
        } catch (e: Exception) {
            onError()
        }
    }

    private companion object {
        const val AUTHORIZATION = "authorization"
    }
}
