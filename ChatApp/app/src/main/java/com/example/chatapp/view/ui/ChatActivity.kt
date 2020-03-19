package com.example.chatapp.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.chatapp.R
import com.example.chatapp.model.entity.Message
import com.example.chatapp.view.adapter.MessageAdapter
import com.example.chatapp.viewmodel.MessageViewModel
import java.util.*

class ChatActivity : AppCompatActivity() {

    //view
    private lateinit var listView: ListView
    private lateinit var editTextTypeMessage: EditText
    private lateinit var btnSend: ImageView
    //properties class
    private lateinit var listMessageViewModel: MessageViewModel
    private lateinit var adapterMessage: MessageAdapter
    private var list:MutableList<Message> = arrayListOf()
    private var currentUser: String = ""
    private var otherUser: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listMessageViewModel = ViewModelProviders.of(this).get(MessageViewModel::class.java)
        supportActionBar!!.elevation = 0f
        recValueFromIntent()
        mapping()
        action()
    }

    // mapping to GUI
    private fun mapping() {
        listView = findViewById(R.id.chatListPassMessage)
        editTextTypeMessage = findViewById(R.id.chatTypeMessage)
        btnSend = findViewById(R.id.chatBtnSend)

        adapterMessage = MessageAdapter(list, this, currentUser)
        listView.adapter = adapterMessage
        adapterMessage.notifyDataSetChanged()
        listView.setSelection(listView.lastVisiblePosition)
    }

    // main action
    private fun action() {
        handleBtnSend()
        displayMessage()
    }

    private fun displayMessage() {
        val liveData = listMessageViewModel.getMessageListLiveData(currentUser, otherUser)
        liveData.observe(this, androidx.lifecycle.Observer<List<Message>> {
            // Update the UI, in this case: a ListView
            list.removeAll(list)
            list.addAll(it)
            adapterMessage.notifyDataSetChanged()
            //scroll list view to end
            listView.setSelection(listView.lastVisiblePosition)
        })
    }

    private fun recValueFromIntent() {
        val intent = intent
        if (intent != null) {
            currentUser = intent.getStringExtra("user")!!
            otherUser = if (currentUser == "user1") {
                "user2"
            } else {
                "user1"
            }
            this.title = currentUser
        }
    }

    //nhan btn send
    private fun handleBtnSend() {
        btnSend.setOnClickListener {
            sendMessage(editTextTypeMessage)
        }
    }

    private fun sendMessage(editText: EditText) {
        if (editText.text != null || editText.text.toString().trim() != "") {
            listMessageViewModel.addMessage(
                Message(
                    currentUser,
                    editText.text.toString().trim(),
                    Calendar.getInstance().timeInMillis,
                    false
                ), currentUser, otherUser
            )
            editText.setText("")
            editText.onEditorAction(EditorInfo.IME_ACTION_DONE)
        } else {
            Toast.makeText(this, "không được phép", Toast.LENGTH_SHORT).show()
        }
    }

}
