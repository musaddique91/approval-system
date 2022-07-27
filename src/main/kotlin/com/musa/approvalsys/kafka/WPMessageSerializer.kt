package com.musa.approvalsys.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.springframework.stereotype.Component

@Component
class WPMessageSerializer(
    private val objectMapper: ObjectMapper
) : Serializer<WPMessage> {
    override fun serialize(p0: String?, p1: WPMessage?): ByteArray {
        return try {
            objectMapper.writeValueAsBytes(p1)
        } catch (e: Exception) {
            throw SerializationException("Error when serializing MessageDto to byte[]")
        }
    }
}