package com.hackeo.ai

import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.config.ConfigDataNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/client-access")
class ClientAccessController(
    val clientAccessService: ClientAccessService
) {
    @PostMapping
    fun createClientAccess(@RequestBody clientAccessDTO: ClientAccessDTO): Mono<ClientAccess> {
        return clientAccessService.addClientRepo(clientAccessDTO)
    }

    @GetMapping(value = ["/stream"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getAllClientAccessStream(): Flux<ClientAccess> {
        return clientAccessService.getAllClientAccess()
    }

    @GetMapping
    fun getAllClientAccess(): Flux<ClientAccess> {
        return clientAccessService.getAllClientAccess()
    }

    @GetMapping(value = ["/{id}"],produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getClientAccessById(@PathVariable("id") id: Long): Mono<ClientAccess> {
        return clientAccessService.getClientAccessById(id)
    }

    @PutMapping(value = ["/{id}"])
    fun updateClientAccess(@PathVariable("id") id: Long, @RequestBody accessDTO: ClientAccessDTO): Mono<ClientAccess> {
        return clientAccessService.updateClientAccess(id, clientAccessDTO = accessDTO)
    }

}


