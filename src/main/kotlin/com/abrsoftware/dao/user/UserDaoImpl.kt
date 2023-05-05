package com.abrsoftware.dao.user

import com.abrsoftware.dao.DatabaseFactory.dbQuery
import com.abrsoftware.model.SignUpParams
import com.abrsoftware.model.User
import com.abrsoftware.model.UserRow
import com.abrsoftware.security.hasPassword
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDaoImpl : UserDao {
    override suspend fun insert(params: SignUpParams): User? {
        return dbQuery {
            val insertStatement = UserRow.insert {
                it[name] = params.name
                it[email] = params.email
                it[password] = hasPassword(params.password)
            }

            insertStatement.resultedValues?.singleOrNull()?.let { resultRow ->
                rowToUser(resultRow)
            }
        }
    }

    override suspend fun findByEmail(email: String): User? {
        return dbQuery {
            UserRow.select { UserRow.email eq email }
                .map { rowToUser(it) }
                .singleOrNull()
        }
    }

    private fun rowToUser(row: ResultRow): User {
        return User(
            id = row[UserRow.id],
            name = row[UserRow.name],
            bio = row[UserRow.bio],
            avatar = row[UserRow.avatar],
            password = row[UserRow.password]
        )
    }
}