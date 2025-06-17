package com.easy.geoWarden.ui.screen.loginScreen


import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.geoWarden.data.repository.AuthRepository
import com.easy.geoWarden.data.user.UserState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber


class LoginViewModel( private val authRepository: AuthRepository):ViewModel() {

    val userState: StateFlow<UserState> = authRepository.userState
    fun Login(email:String,password:String){
        Timber.tag(TAG).d("Tentando fazer login com o email")
        viewModelScope.launch { authRepository.login(email, password) }

    }

}