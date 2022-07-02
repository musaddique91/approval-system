package com.musa.approvalsys.services

import com.musa.approvalsys.mail.MailComposeDTO
import com.musa.approvalsys.mail.MailSender
import freemarker.template.Configuration
import org.springframework.stereotype.Service
import java.io.StringWriter

@Service
class MailExecutorServcie(
    private val configuration: Configuration,
    private val mailSender: MailSender
) {

    fun sendWelcomeMail(toEmail: String, name: String) {
        val modal = mutableMapOf<String, String>()
        modal["name"] = name
        val writer = StringWriter()
        configuration.getTemplate("welcome.ftlh").process(modal, writer)
        val data = MailComposeDTO(
            to = toEmail,
            subject = "Welcome",
            templateContent = writer.buffer.toString()
        )
        mailSender.send(data)
    }

}