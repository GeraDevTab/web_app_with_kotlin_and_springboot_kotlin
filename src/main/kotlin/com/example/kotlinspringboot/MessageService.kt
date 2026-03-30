package com.example.kotlinspringboot

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(private val db: JdbcTemplate) {
    fun findMessages(): List<Message> = db.query("SELECT * FROM messages") { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

    fun saveMessage(message: Message): Message {
        db.update(
            "INSERT INTO messages values ( ?, ?)",
            message.id, message.text
        )
        return message
    }
}