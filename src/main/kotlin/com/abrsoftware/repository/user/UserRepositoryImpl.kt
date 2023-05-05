package com.abrsoftware.repository.user

import com.abrsoftware.dao.user.UserDao
import com.abrsoftware.model.AuthResponse
import com.abrsoftware.model.AuthResponseData
import com.abrsoftware.model.SignInParams
import com.abrsoftware.model.SignUpParams
import com.abrsoftware.plugins.generateToken
import com.abrsoftware.security.hasPassword
import com.abrsoftware.util.Response
import io.ktor.http.*

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun signUp(params: SignUpParams): Response<AuthResponse> {
        return if (userAlreadyExist(params.email)) {
            Response.Error(
                code = HttpStatusCode.Conflict,
                data = AuthResponse(
                    errorMessage = "A user this email al ready exists!"
                )
            )
        } else {
            val insertedUser = userDao.insert(params)
            if (insertedUser == null) {
                Response.Error(
                    code = HttpStatusCode.InternalServerError,
                    data = AuthResponse(
                        errorMessage = "Oops, sorry we couldn't register the user, try later!"
                    )
                )
            } else {
                Response.Succes(
                    data = AuthResponse(
                        data = AuthResponseData(
                            id = insertedUser.id,
                            name = insertedUser.name,
                            bio = insertedUser.bio,
                            token = generateToken(params.email)
                        )
                    )
                )
            }
        }
    }

    override suspend fun signIn(params: SignInParams): Response<AuthResponse> {
        val user = userDao.findByEmail(params.email)

        return if (user == null) {
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = AuthResponse(
                    errorMessage = "Invalid credentials with this email"
                )

            )
        } else {
            val hashedPassword = hasPassword(params.password)
            if (user.password == hashedPassword) {
                Response.Succes(
                    data = AuthResponse(
                        data = AuthResponseData(
                            id = user.id,
                            name = user.name,
                            bio = user.bio,
                            token = generateToken(params.email)
                        )
                    )
                )
            } else {
                Response.Error(
                    code = HttpStatusCode.Forbidden,
                    data = AuthResponse(
                        errorMessage = "Invalid credentials with this email"
                    )
                )
            }
        }
    }

    private suspend fun userAlreadyExist(email: String): Boolean {
        return userDao.findByEmail(email) != null
    }
}