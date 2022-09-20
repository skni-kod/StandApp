package com.sknikod.standapp.domain.repository

import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.domain.model.Article
import com.sknikod.standapp.domain.model.Project
import com.sknikod.standapp.uti.Result

abstract class Repository( val client: RestApiClient) {

}
