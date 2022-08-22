package com.hackeo.ai

import org.springframework.data.annotation.Id
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import javax.annotation.Generated


@Table("client_access")
data class ClientAccess(
    @Id
    @Generated
    val id: Long? = null,

    @Column( "firstname")
    var firstname: String,

    @Column("lastname")
    var lastname: String,

    @Column("email")
    var email: String,

    @Column("ip_address")
    var ipAddress: String
){
    fun toDto() = ClientAccessDTO(
        firstname = this.firstname,
        lastname = this.lastname,
        email = this.email,
        ipAddress = this.ipAddress
    )
}

data class ClientAccessDTO(
    var firstname: String,
    var lastname: String,
    var email: String,
    var ipAddress: String
)

@Component
interface ClientAccessRepo: ReactiveCrudRepository<ClientAccess, Long>{

    @Query("Select * from client_access where id=:id")
    fun findClientAccessById(@Param("id") id:Long): Mono<ClientAccess>

    @Query("update client_access set firstname=:firstname where id=:id")
    fun updateClientAccess(
        @Param("id") id: Long,
        @Param("firstName") firstname: String
    )

}