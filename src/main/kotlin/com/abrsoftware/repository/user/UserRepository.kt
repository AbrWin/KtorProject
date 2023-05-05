package com.abrsoftware.repository.user

import com.abrsoftware.model.AuthResponse
import com.abrsoftware.model.SignInParams
import com.abrsoftware.model.SignUpParams
import com.abrsoftware.util.Response


interface UserRepository {
    suspend fun signUp(params: SignUpParams): Response<AuthResponse>
    suspend fun signIn(params: SignInParams): Response<AuthResponse>
}