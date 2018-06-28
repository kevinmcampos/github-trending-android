package me.kevincampos.data.mapper

interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D

    fun mapFromDomain(domain: D): E

}