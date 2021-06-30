package com.example.domain

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,
        @Column(name = "productList")
        @OneToMany(orphanRemoval=true)
        @JoinColumn(name="PROD_ID")
        var productList: List<Product>,
        @Column(name = "contactName")
        var contactName: String,
        @Column(name = "contactNumber")
        var contactNumber: String,
        @Column(name = "contactEmail")
        var contactEmail: String,
        @Column(name = "observation")
        var observation: String,
        @Column(name = "createdAt")
        var createdAt: Instant
)

/**
Olá, meu nome é asdasda
esse é o meu pedido feito no seu catalogo: 1x Branco Casual Estrela(R$65,00) = *R$65,00*
Tamanho : 24 https://meucatalogo.app/mundokids/product/6053a22c4f19ba00074e09f7
1x Sandália Arco Íris (R$65,00) = *R$65,00*
Tamanho : 21 https://meucatalogo.app/mundokids/product/605399b04f19ba00074e052b
 *TOTAL R$130,00* Informações adicionais: _asdasdasdasd_
 */