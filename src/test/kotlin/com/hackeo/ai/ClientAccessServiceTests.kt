package com.hackeo.ai

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class ClientAccessServiceTests {

    @Autowired
    private lateinit var clientAccessService: ClientAccessService

    @Autowired
    private lateinit var clientAccessRepo: ClientAccessRepo

    @BeforeEach
    fun setup(){
        clientAccessRepo.deleteAll().block()
    }

    @Test
    fun `should add a new record to the DB`() {
        val mockData = ClientAccess(
            firstname = "test",
            lastname = "test",
            email = "test@test.com",
            ipAddress = "0.0.0.0",
            id = null
        )
        var test = clientAccessRepo.save(mockData).block()
        Assertions.assertNotNull(test)
    }

    @Test
    fun `should list all client access records`() {
        val mockData1 = ClientAccess(
            firstname = "test1",
            lastname = "test1",
            email = "test1@test.com",
            ipAddress = "0.0.0.0",
        )
        val mockData2 = ClientAccess(
            firstname = "test2",
            lastname = "test2",
            email = "test2@test.com",
            ipAddress = "0.0.0.0",
        )
        var test = clientAccessRepo.saveAll(mutableListOf(mockData1,mockData2)).collectList().block()
        Assertions.assertTrue(test!!.size == 2)
    }


    @Test
    fun `should update client access record`() {
        val mockData = ClientAccess(
            firstname = "test1",
            lastname = "test1",
            email = "test1@test.com",
            ipAddress = "0.0.0.0"
        )
        val cae = clientAccessRepo.save(mockData).block()
        val toUpdate = ClientAccessDTO(
            firstname = "Test2",
            lastname = "Test2",
            email = "test2@gmail.com",
            ipAddress = "1.1.1.1"
        )
        val updated = clientAccessService.updateClientAccess(cae!!.id!!,toUpdate).block()

        Assertions.assertTrue(cae!!.id!! == updated!!.id)
        Assertions.assertEquals("Test2",updated.firstname)
    }
}