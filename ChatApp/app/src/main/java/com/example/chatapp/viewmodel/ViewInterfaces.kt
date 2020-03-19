package com.example.chatapp.viewmodel

import androidx.lifecycle.LiveData
import com.example.chatapp.model.entity.Message

class ViewInterfaces {
    interface MessageViewModelInterFace {
        fun addMessage(message: Message, currentUser: String, otherUser: String)

        fun getMessageListLiveData(currentUser: String, otherUser: String): LiveData<List<Message>>
    }
}