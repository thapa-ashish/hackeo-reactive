package com.hackeo.ai

import kotlinx.coroutines.reactor.awaitLast
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration


@Service
class ClientAccessService(
    private val clientAccessRepo: ClientAccessRepo
) {
    fun addClientRepo(clientAccessDTO: ClientAccessDTO): Mono<ClientAccess> {
        return clientAccessRepo.save(
            ClientAccess(
                firstname = clientAccessDTO.firstname,
                lastname = clientAccessDTO.lastname,
                email = clientAccessDTO.email,
                ipAddress = clientAccessDTO.ipAddress
            )
        )
    }

    fun getAllClientAccess(): Flux<ClientAccess> {
        return clientAccessRepo.findAll()
    }

    fun getClientAccessById(id: Long): Mono<ClientAccess> {
        return clientAccessRepo.findClientAccessById(id)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")))

    }

    fun updateClientAccess(id: Long, clientAccessDTO: ClientAccessDTO): Mono<ClientAccess> {
        return clientAccessRepo.findById(id).flatMap {
            it.email = clientAccessDTO.email
            it.firstname = clientAccessDTO.firstname
            it.lastname = clientAccessDTO.lastname
            it.ipAddress = clientAccessDTO.ipAddress

            clientAccessRepo.save(it)
        }.switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")))
    }

}