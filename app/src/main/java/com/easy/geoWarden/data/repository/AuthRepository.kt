package com.easy.geoWarden.data.repository

import android.content.ContentValues.TAG
import com.easy.geoWarden.data.user.UserState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class AuthRepository {
    private  var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _userState = MutableStateFlow<UserState>(UserState.noSession)
    var userState: StateFlow<UserState> = _userState

    init {
        auth.addAuthStateListener { auth ->
            _userState.value = if (auth.currentUser != null) {
                UserState.LoggedIn(auth.currentUser!!)
            } else {
                UserState.noSession
            }
        }
    }


    suspend fun login(email: String, password: String) {
        try {
            _userState.value = UserState.Loading
            auth.signInWithEmailAndPassword(email, password).await()
            _userState.value = UserState.LoggedIn(auth.currentUser!!)
        } catch (e: Exception){
            _userState.value = UserState.Error(e.message ?: "Login failed")
            Timber.tag(TAG).w(e, "Falha no login")

        }
    }

}