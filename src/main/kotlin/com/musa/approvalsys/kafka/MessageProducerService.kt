package com.musa.approvalsys.kafka

import com.musa.approvalsys.config.ApprovalProperties
import com.musa.approvalsys.constants.Constants
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageProducerService(
    private val kafkaTemplate: KafkaTemplate<String, WPMessage>,
) {
    fun send(message: WPMessage){
        kafkaTemplate.send(Constants.WP_TOPIC, message)
    }
}