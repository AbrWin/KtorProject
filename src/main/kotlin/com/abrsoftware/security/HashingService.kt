package com.abrsoftware.security

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val ALGORITHM = System.getenv("HASH_ALGORITHM")
private val HASH_KEY = System.getenv("HASH_SECRET").toByteArray()
private val hMacKey = SecretKeySpec(HASH_KEY, ALGORITHM)

fun hasPassword(password: String): String {
    val hMac = Mac.getInstance(ALGORITHM)
    hMac.init(hMacKey)
    return hex(hMac.doFinal(password.toByteArray()))
}