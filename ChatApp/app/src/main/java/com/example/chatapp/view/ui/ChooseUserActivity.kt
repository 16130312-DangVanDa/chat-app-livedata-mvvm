package com.example.chatapp.view.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.R

class ChooseUserActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var btnOK: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_activity)

        supportActionBar!!.elevation = 0f
        mapping()
        action()
    }

    private fun mapping() {
        radioGroup = findViewById(R.id.chooseUserGroup)
        btnOK = findViewById(R.id.chooseOk)
    }

    private fun action() {
        var user = "user1"
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            user = if (R.id.chooseUser1 == checkedId) {
                "user1"
            } else {
                "user2"
            }
        }

        btnOK.setOnClickListener {
            //chuyen sang activity
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }
}