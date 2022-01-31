package com.sknikod.standapp.data.remote.repository

import android.util.Log
import com.google.android.play.core.splitinstall.x
import com.sknikod.standapp.data.remote.StandappApi
import com.sknikod.standapp.domain.model.ProjectItem
import com.sknikod.standapp.domain.model.ProjectList
import com.sknikod.standapp.domain.repository.StandappRepository
import com.sknikod.standapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class StandappRepositoryImpl @Inject constructor(
    private val api:StandappApi
): StandappRepository()
{
    override suspend fun getProject(id: Int?): NetworkResult<ProjectItem> {
        return   apiCall { api.getProject(id) }

    }

    override fun getProjects(): Flow<NetworkResult<ProjectList>> {
        TODO("Not yet implemented")
    }

}