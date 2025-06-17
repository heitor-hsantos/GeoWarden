package com.easy.geoWarden.data.user

sealed class UserState {

    data object UserSession: UserState()
    data object noSession: UserState()
    data object Loading: UserState()


    data class LoggedIn(val firebaseUser:com.google.firebase.auth.FirebaseUser): UserState()


    data class Error(val message:String): UserState()
}
