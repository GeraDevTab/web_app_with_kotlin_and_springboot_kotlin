package com.example.kotlinspringboot

import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, String>