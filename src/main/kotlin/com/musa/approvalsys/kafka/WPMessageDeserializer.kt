package com.musa.approvalsys.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.springframework.stereotype.Component
import java.nio.charset.Charset

@Component
class WPMessageDeserializer(
    private val objectMapper: ObjectMapper
) : Deserializer<WPMessage> {
    override fun deserialize(p0: String?, p1: ByteArray?): WPMessage {
        return try {
            objectMapper.readValue<WPMessage>(String(p1!!, Charset.forName("UTF-8")), WPMessage::class.java)
        } catch (e: Exception) {
            throw SerializationException("Error when deserializing byte[] to MessageDto")
        }
    }
}