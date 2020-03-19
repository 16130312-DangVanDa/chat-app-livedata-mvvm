package com.example.chatapp.model.repository.message

import androidx.lifecycle.LiveData
import com.example.chatapp.model.entity.Message

class RepositoryInterface {

    interface MessageInterface {

        fun add(message: Message, currentUser: String, otherUser: String)

        fun get(currentUser: String, otherUser: String): LiveData<List<Message>>

    }
}