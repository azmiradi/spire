package com.azmiradi.kotlin_base.domain.mapper

abstract class Mapper<BaseInput, Domain> {

    /**
     * Mapping Dto to Domain
     *
     * @param model is the dto class.
     * @return Domain class of the Dto Feature.
     */
    open fun dtoToDomain(model: BaseInput): Domain =
        throw NotImplementedError ("override and implement this method")

    /**
     * Mapping Domain to Dto
     *
     * @param model is the domain class.
     * @return Dto class of the Domain Feature.
     */
    open fun domainToDto(model: Domain): BaseInput =
        throw NotImplementedError("override and implement this method")

    /**
     * Mapping Dto list to Domain list
     *
     * @param list is the list of dto class.
     * @return Domain list of the Dto list feature.
     */
    fun dtoToDomain(list: List<BaseInput>?): List<Domain> = (list ?: emptyList()).map(::dtoToDomain)

    /**
     * Mapping Domain list to Dto list
     *
     * @param list is the list of domain class.
     * @return Dto list of the Domain list feature.
     */
    fun domainToDto(list: List<Domain>?): List<BaseInput> = (list ?: emptyList()).map(::domainToDto)

    /**
     * Mapping Entity to Domain class.
     *
     * @param model is the entity class.
     * @return Domain class.
     */
    open fun entityToDomain(model: BaseInput): Domain =
        throw NotImplementedError("override and implement this method")

    /**
     * Mapping Domain to Entity class.
     *
     * @param model is the domain class.
     * @return Entity class.
     */
    open fun domainToEntity(model: Domain): BaseInput =
        throw NotImplementedError("override and implement this method")

    /**
     * Mapping Entity list to Domain list
     *
     * @param list is the list of entity class.
     * @return Domain list of the Entity list feature.
     */
    fun entityToDomain(list: List<BaseInput>): List<Domain> = list.map(::entityToDomain)

    /**
     * Mapping Domain list to Entity list
     *
     * @param list is the list of domain class.
     * @return Entity list of the Domain list feature.
     */
    fun domainToEntity(list: List<Domain>): List<BaseInput> = list.map(::domainToEntity)

    /**
     * Mapping Entity to Dto class.
     *
     * @param model is the entity class.
     * @return Dto class.
     */
    open fun entityToDto(model: BaseInput): BaseInput =
        throw NotImplementedError("override and implement this method")

    /**
     * Mapping Dto to Entity class.
     *
     * @param model is the dto class.
     * @return Entity class of the Dto Feature.
     */
    open fun dtoToEntity(model: BaseInput): BaseInput =
        throw NotImplementedError("override and implement this method")


    /**
     * Mapping Entity list to Dto list
     *
     * @param list is the list of entity class.
     * @return Dto list of the Entity list feature.
     */
    fun entityToDto(list: List<BaseInput>): List<BaseInput> = list.map(::entityToDto)

    /**
     * Mapping Dto list to Entity list
     *
     * @param list is the list of dto class.
     * @return Entity list of the Dto list feature.
     */
    fun dtoToEntity(list: List<BaseInput>): List<BaseInput> = list.map(::dtoToEntity)
}