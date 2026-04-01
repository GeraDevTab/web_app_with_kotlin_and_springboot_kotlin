package com.example.kotlinspringboot

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MessageService(private val db: JdbcTemplate) {
    fun findMessages(): List<Message> = db.query("SELECT * FROM messages") { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

    fun saveMessage(message: Message): Message {
        val id = message.id ?: UUID.randomUUID().toString() //generate id if it is null
        db.update(
            "INSERT INTO messages values ( ?, ?)",
            id, message.text
        )
        return message.copy(id = id) //return a copy of the message with the new id
    }
}