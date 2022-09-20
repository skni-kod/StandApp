package com.sknikod.standapp

import com.sknikod.standapp.data.client.RestApiClientKtorImpl
import com.sknikod.standapp.data.repository.RepositoryProjectImpl
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class RepositoryImplTest {

    @Test
    fun `getProjects_empty data_return 200`() = runTest {
        val mockClient = MockEngine { request ->
            respond(
                content = ByteReadChannel(
                    """
               [ {
        "id": 25,
        "title": "MicrOS DevTools CPP",
        "text": "MicrOS DevTools CPP zostły opracowane aby zastąpić poprzednią wersję Dev Toolsów napisaną w C#. Mają stanowić bardziej dopracowaną wersję narzędzia. Oparte będą o bibliotekę Qt5.---readmore---\n\n# Funkcjonalności\nW skład DevToolsów wejdą:\n- ekran powitalny\n- ekran przedstawiający potrzebne oprogramowanie\n- ekran do konfiguracji MSYSa\n- ekran do pobierania kompilatorów\n- ekran do pobierania obrazu dysków i dyskietek\n- ekran do pobierania i konfigurowania skryptów",
        "creation_date": "2022-02-22T21:10:36.942000+01:00",
        "publication_date": "2022-02-22T21:10:36.942000+01:00",
        "creator": {
            "id": 2,
            "username": "pmiekina",
            "email": "pawel_mibor95@tlen.pl",
            "first_name": "Paweł",
            "last_name": "Miękina",
            "profile": {
                "id": 2,
                "description": null,
                "links": [],
                "index_number": null
            }
        },
        "section": {
            "id": 1,
            "name": "Sekcja Aplikacji Desktopowych Mobilnych i Webowych",
            "description": "Sekcja zajmująca się budową aplikacji na różnego rodzaju urządzenia, wykorzystując szeroką gamę technologii.",
            "isVisible": true,
            "icon": "cellphone-link",
            "gallery": []
        },
        "authors": [
            {
                "id": 2,
                "username": "pmiekina",
                "email": "pawel_mibor95@tlen.pl",
                "first_name": "Paweł",
                "last_name": "Miękina",
                "profile": {
                    "id": 2,
                    "description": null,
                    "links": [],
                    "index_number": null
                }
            }
        ],
        "gallery": [
            {
                "id": 208,
                "image": "/media/gallery/MicrOS_DevTools_CPP_1.png",
                "thumbnail": "/media/cache/c7/d7/c7d71a31330441ecdacfd73633849859.jpg",
                "thumbnail_visibility": false,
                "text_visibility": false,
                "gallery_visibility": false
            },
            {
                "id": 209,
                "image": "/media/gallery/MicrOS_DevTools_CPP_2.png",
                "thumbnail": "/media/cache/88/f4/88f4ac789c73535bcda2349ce1cb2880.jpg",
                "thumbnail_visibility": false,
                "text_visibility": false,
                "gallery_visibility": false
            },
            {
                "id": 210,
                "image": "/media/gallery/MicrOS_DevTools_CPP_3.png",
                "thumbnail": "/media/cache/8a/4a/8a4a52bec509db26dbe167e763592fa8.jpg",
                "thumbnail_visibility": false,
                "text_visibility": false,
                "gallery_visibility": false
            },
            {
                "id": 211,
                "image": "/media/gallery/MicrOS_DevTools_CPP_4.png",
                "thumbnail": "/media/cache/51/83/51835a2bf9f73130a880eafb318ee2f3.jpg",
                "thumbnail_visibility": false,
                "text_visibility": false,
                "gallery_visibility": false
            }
        ],
        "links": []
    }]
    
                    """.trimIndent()
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val mockClientToken = MockEngine { request ->
            respond(
                content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = RestApiClientKtorImpl(mockClient, mockClientToken)
        val repositoryProjectImpl = RepositoryProjectImpl(client)
        assertEquals(true, repositoryProjectImpl.getListOfProjects().value?.isNotEmpty())
    }
}
