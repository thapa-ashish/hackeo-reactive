package com.hackeo.ai

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ClientAccessControllerTests{

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `should return all access records`(){
        webTestClient.get()
            .uri("/v1/client-access/stream")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `should add a access record`(){
        val mockData = ClientAccessDTO(
            firstname = "test",
            lastname = "test",
            email = "test",
            ipAddress = "1.1.1.1"
        )
        webTestClient.post().uri("/v1/client-access")
            .bodyValue(mockData)
            .exchange()
            .expectStatus().isOk
            .expectBody<ClientAccess>().consumeWith{
                Assertions.assertTrue(it.responseBody!!.email == "test")
            }
    }
}
