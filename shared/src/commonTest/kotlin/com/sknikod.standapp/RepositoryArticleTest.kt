package com.sknikod.standapp

import com.sknikod.standapp.data.client.RestApiClientKtorImpl
import com.sknikod.standapp.data.repository.RepositoryArticleImpl
import com.sknikod.standapp.domain.model.Article
import com.sknikod.standapp.uti.Result
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlinx.coroutines.test.runTest

internal class RepositoryArticleTest {
    @Test
    fun `getArticles_valid data_value is not empty `() = runTest {
        val mockClient = MockEngine { request ->
            respond(
                content = ByteReadChannel(
                    """[{
    "id": 25,
    "alias": "Historyczne-postacie-#5:-Linux-Torvalds",
    "title": "Historyczne postacie #5: Linus Torvalds",
    "text": "Linus Benedict Torvalds urodził się w 1969 roku w Helsinkach i jest Finem szwedzkojęzycznym.---readmore--- Ukończył Uniwersytet Helsiński. Będąc zainspirowanym edukacyjnym systemem MINIX (stworzonym przez Andrew Tanenbauma), poczuł potrzebę posiadania własnego wydajnego systemu operacyjnego typu UNIX. Stworzył on więc jądro LINUX, które powstawało głównie w jego wolnym czasie i przy użyciu własnego sprzętu.\r\n\r\nPierwsza wersja ujrzała światło dzienne 15 października 1991 roku. Z czasem Linux stał się rozległym, złożonym projektem, w którym dobrowolnie udziela się wiele osób.\r\n\r\nObecnie Linus Torvalds przede wszystkim nadzoruje jego rozwój, a opiekę nad podzespołami przekazuje licznym współpracownikom. Rozwój Linuksa traktuje jako proces ewolucyjny, kierowany potrzebami twórców i użytkowników. W odróżnieniu od innych, Torvalds unika rozgłosu i zasadniczo odmawia komentarzy dotyczących konkurencyjnego oprogramowania, takiego jak np Microsoft Windows.\r\n\r\nŹródła: [1](https://teamquest.pl/blog/1439_torvalds-nie-znam-sie-na-programowaniu?fbclid=IwAR3GRTsMvRtjMcesC-Bjrkbnoi3RsFUArg5Qt4h2sSKAvsfvikrhJSZLJXI) [2](https://pl.wikipedia.org/wiki/Linus_Torvalds)",
    "creation_date": "2021-05-12T17:29:03+02:00",
    "group": "News",
    "publication_date": "2021-05-12T17:29:05+02:00",
    "creator": {
        "id": 2,
        "username": "test",
        "email": "test",
        "first_name": "test",
        "last_name": "test",
        "profile": {
            "id": 2,
            "description": null,
            "links": [],
            "index_number": null
        }
    },
    "authors": [
        {
        "id": 2,
        "username": "test",
        "email": "test",
        "first_name": "test",
        "last_name": "test",
            "profile": {
                "id": 2,
                "description": null,
                "links": [],
                "index_number": null
            }
        }
    ],
    "tags": [
        {
            "id": 8,
            "name": "historyczne-postacie"
        }
    ],
    "comments_number": 0,
    "gallery": [
        {
            "id": 41,
            "image": "/media/gallery/linus.jpg",
            "thumbnail": "/media/cache/43/c5/43c580435cea7cf02c30960fdfe0e560.jpg",
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
        val repositoryArticleImpl = RepositoryArticleImpl(client)
        val acting = repositoryArticleImpl.getListOfArticles()
        assertEquals(true, acting.value?.isNotEmpty())
    }

    @Test
    fun `getArticles_empty or invalid data_ instance class is Result Error`() = runTest {
        val mockClient = MockEngine { request ->
            respond(
                content = ByteReadChannel(
                    ""
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
        val repositoryArticleImpl = RepositoryArticleImpl(client)
        val acting = repositoryArticleImpl.getListOfArticles()
        assertIs<Result.Error<List<Article>>>(acting)
    }

    @Test
    fun `getArticles_empty array_instance class is Result Success`() = runTest {
        val mockClient = MockEngine { request ->
            respond(
                content = ByteReadChannel(
                    "[]"
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
        val repositoryArticleImpl = RepositoryArticleImpl(client)
        val acting = repositoryArticleImpl.getListOfArticles()
        assertIs<Result.Success<List<Article>>>(acting)
    }

    @Test
    fun `getArticle_valid data_ value is correct type class `() = runTest {
        val mockClient = MockEngine { request ->
            respond(
                content = ByteReadChannel(
                    """{
    "id": 25,
    "alias": "Historyczne-postacie-#5:-Linux-Torvalds",
    "title": "Historyczne postacie #5: Linus Torvalds",
    "text": "Linus Benedict Torvalds urodził się w 1969 roku w Helsinkach i jest Finem szwedzkojęzycznym.---readmore--- Ukończył Uniwersytet Helsiński. Będąc zainspirowanym edukacyjnym systemem MINIX (stworzonym przez Andrew Tanenbauma), poczuł potrzebę posiadania własnego wydajnego systemu operacyjnego typu UNIX. Stworzył on więc jądro LINUX, które powstawało głównie w jego wolnym czasie i przy użyciu własnego sprzętu.\r\n\r\nPierwsza wersja ujrzała światło dzienne 15 października 1991 roku. Z czasem Linux stał się rozległym, złożonym projektem, w którym dobrowolnie udziela się wiele osób.\r\n\r\nObecnie Linus Torvalds przede wszystkim nadzoruje jego rozwój, a opiekę nad podzespołami przekazuje licznym współpracownikom. Rozwój Linuksa traktuje jako proces ewolucyjny, kierowany potrzebami twórców i użytkowników. W odróżnieniu od innych, Torvalds unika rozgłosu i zasadniczo odmawia komentarzy dotyczących konkurencyjnego oprogramowania, takiego jak np Microsoft Windows.\r\n\r\nŹródła: [1](https://teamquest.pl/blog/1439_torvalds-nie-znam-sie-na-programowaniu?fbclid=IwAR3GRTsMvRtjMcesC-Bjrkbnoi3RsFUArg5Qt4h2sSKAvsfvikrhJSZLJXI) [2](https://pl.wikipedia.org/wiki/Linus_Torvalds)",
    "creation_date": "2021-05-12T17:29:03+02:00",
    "group": "News",
    "publication_date": "2021-05-12T17:29:05+02:00",
    "creator": {
        "id": 2,
        "username": "test",
        "email": "test",
        "first_name": "test",
        "last_name": "test",
        "profile": {
            "id": 2,
            "description": null,
            "links": [],
            "index_number": null
        }
    },
    "authors": [
        {
        "id": 2,
        "username": "test",
        "email": "test",
        "first_name": "test",
        "last_name": "test",
            "profile": {
                "id": 2,
                "description": null,
                "links": [],
                "index_number": null
            }
        }
    ],
    "tags": [
        {
            "id": 8,
            "name": "historyczne-postacie"
        }
    ],
    "comments_number": 0,
    "gallery": [
        {
            "id": 41,
            "image": "/media/gallery/linus.jpg",
            "thumbnail": "/media/cache/43/c5/43c580435cea7cf02c30960fdfe0e560.jpg",
            "thumbnail_visibility": false,
            "text_visibility": false,
            "gallery_visibility": false
        }
    ],
    "links": []
}
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
        val repositoryArticleImpl = RepositoryArticleImpl(client)
        val randomNumber = 25
        val acting = repositoryArticleImpl.getArticle(randomNumber)
        assertIs<Article>(acting.value)
    }

    @Test
    fun `getArticle_invalid valid data_ instance class is Result Error`() = runTest {
        val mockClient = MockEngine { request ->
            respond(
                content = ByteReadChannel(
                    " "
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
        val repositoryArticleImpl = RepositoryArticleImpl(client)
        val randomNumber = 25
        val acting = repositoryArticleImpl.getArticle(randomNumber)
        assertIs<Result.Error<List<Article>>>(acting)
    }

    @Test
    fun `getArticle_empty array_ instance class is Result Error`() = runTest {
        val mockClient = MockEngine { request ->
            respond(
                content = ByteReadChannel(
                    "[]"
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
        val repositoryArticleImpl = RepositoryArticleImpl(client)
        val randomNumber = 25
        val acting = repositoryArticleImpl.getArticle(randomNumber)
        assertIs<Result.Error<Article>>(acting)
    }
}
