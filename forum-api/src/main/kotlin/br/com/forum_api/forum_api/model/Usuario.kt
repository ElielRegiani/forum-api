package br.com.forum_api.forum_api.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val email: String,
    val password: String,

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_role", // nome da tabela intermediária
        joinColumns = [JoinColumn(name = "usuario_id")], // FK para Usuario
        inverseJoinColumns = [JoinColumn(name = "role_id")] // FK para Role
    )
    val role: List<Role> = mutableListOf()
)
