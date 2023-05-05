package com.abrsoftware.dao.user

import com.abrsoftware.model.SignUpParams
import com.abrsoftware.model.User

interface UserDao {
    suspend fun insert(params: SignUpParams): User?
    suspend fun findByEmail(email: String): User?
}