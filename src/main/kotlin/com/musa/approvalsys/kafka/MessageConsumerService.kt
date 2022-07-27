package com.musa.approvalsys.kafka

import com.musa.approvalsys.constants.Constants
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageConsumerService() {
    @KafkaListener(topics = [Constants.WP_TOPIC], groupId = Constants.WP_GROUP)
    fun consumer(message: WPMessage) {
        println(message)
    }
}