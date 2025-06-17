package com.easy.geoWarden.ui.screen.configuration


import androidx.lifecycle.ViewModel
import com.easy.geoWarden.data.repository.AuthRepository
import com.easy.geoWarden.data.user.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ConfigurationViewModel @Inject constructor(
    private val authRepository: AuthRepository ) : ViewModel() {
   val userState: StateFlow<UserState> = authRepository.userState.stateIn(
        scope = kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Default),
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = UserState.noSession
   )
}