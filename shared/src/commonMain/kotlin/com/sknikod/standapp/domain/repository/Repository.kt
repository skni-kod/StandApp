package com.sknikod.standapp.domain.repository

import com.sknikod.standapp.domain.client.RestApiClient

abstract class Repository(val client: RestApiClient)
