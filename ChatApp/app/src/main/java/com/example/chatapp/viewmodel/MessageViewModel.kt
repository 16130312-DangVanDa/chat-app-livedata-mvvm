package com.example.chatapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.model.entity.Message
import com.example.chatapp.model.repository.message.MessageImpl
import com.example.chatapp.model.repository.message.RepositoryInterface

class MessageViewModel : ViewModel(), ViewInterfaces.MessageViewModelInterFace {
    private val messageDAO: RepositoryInterface.MessageInterface = MessageImpl()

    override fun addMessage(message: Message, currentUser: String, otherUser: String) {
        messageDAO.add(message, currentUser, otherUser)
    }

    override fun getMessageListLiveData(
        currentUser: String,
        otherUser: String
    ): LiveData<List<Message>> {
        val list1 = messageDAO.get(currentUser, otherUser)
        val list2 = messageDAO.get(otherUser, currentUser)

        val messages = MediatorLiveData<List<Message>>()

        messages.addSource(list1) { result ->
            result?.let { messages.value = it }
        }
        messages.addSource(list2) { result ->
            result?.let { messages.value = it }
        }

        return messages
    }
}