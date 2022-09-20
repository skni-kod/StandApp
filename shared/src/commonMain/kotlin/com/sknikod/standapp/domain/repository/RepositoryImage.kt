package com.sknikod.standapp.domain.repository

import com.sknikod.standapp.uti.Result

interface RepositoryImage {
    suspend fun getImage(path: String): Result<ByteArray>
}