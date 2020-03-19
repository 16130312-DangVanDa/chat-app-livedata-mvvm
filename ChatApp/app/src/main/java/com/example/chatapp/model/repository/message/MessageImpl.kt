package com.example.chatapp.model.repository.message

import android.util.Log
import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.chatapp.model.entity.Message
import com.google.firebase.database.*

class MessageImpl : RepositoryInterface.MessageInterface {

    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val listMessage = ArrayList<Message>()

    //add message FB
    override fun add(message: Message, currentUser: String, otherUser: String) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                val path = if (p0.hasChild("message/$otherUser-$currentUser")) {
                    "message/$otherUser-$currentUser"
                } else {
                    "message/$currentUser-$otherUser"
                }
                Log.d("GETDATA", "Lay du lieu len")
                database.child(path).push().setValue(message)
            }
        })
    }

    //get list message between A & B
    override fun get(currentUser: String, otherUser: String): LiveData<List<Message>> {
        val live1: LiveData<DataSnapshot> =
            FirebaseQueryData(database.child("message/$currentUser-$otherUser"))
        return Transformations.map(live1, Deserializer())
    }

    private inner class Deserializer : Function<DataSnapshot, List<Message>> {
        override fun apply(input: DataSnapshot?): List<Message> {
            Log.d("TESTER", "model: $input ")
            val msg = input!!.getValue(Message::class.java)
            msg!!.codeFB = input.key!!
            listMessage.add(msg)

            return listMessage
        }
    }
}