package com.hackeo.ai

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class HackeoTest {

    @Test
    fun monoTest(){
        val publisher = Mono.just("hackeo").log()
        publisher.subscribe { println(it) }
    }

    @Test
    fun fluxTest(){
        val publisher = Flux.just("hackeo","hackathon","spring","api").log()
        publisher.subscribe { println(it) }
    }
}

class MonoTest{

}