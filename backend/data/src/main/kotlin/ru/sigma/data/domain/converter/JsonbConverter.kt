package ru.sigma.data.domain.converter

import jakarta.persistence.AttributeConverter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class JsonbConverter : AttributeConverter<Map<String, Any>, String> {
    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(attribute: Map<String, Any>?): String? {
        return attribute?.let { objectMapper.writeValueAsString(it) }
    }

    override fun convertToEntityAttribute(dbData: String?): Map<String, Any>? {
        return dbData?.let { objectMapper.readValue(it, Map::class.java) as Map<String, Any> }
    }
}