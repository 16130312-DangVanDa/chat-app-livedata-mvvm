package com.example.chatapp.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.chatapp.R
import com.example.chatapp.model.entity.Message
import com.example.chatapp.model.my.util.MyUtils

class MessageAdapter(
    var list: MutableList<Message>,
    var context: Context,
    var currentUser: String
) : BaseAdapter() {

    private class ViewHolder {

        lateinit var time: TextView
        lateinit var bodyMessage: TextView
//        lateinit var love: ImageView//icon switch
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewResult: View?

        val holder = ViewHolder()
        val messageInflater: LayoutInflater =
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val messageThat: Message = list[position]

        if (messageThat.sendBy == currentUser) {
            //getView
            viewResult = messageInflater.inflate(R.layout.my_message, null)
            holder.bodyMessage = viewResult.findViewById(R.id.myMessageBody)
            holder.time = viewResult.findViewById(R.id.myMessageTime)
            viewResult!!.tag = holder
            holder.bodyMessage.text = messageThat.text
            holder.time.text = MyUtils().convertLongToTime(messageThat.time, "HH:mm")
            //set value
        } else {
            //cua user khac
            viewResult = messageInflater.inflate(R.layout.other_message, null)
            holder.bodyMessage = viewResult.findViewById(R.id.otherMessageBody)
            holder.time = viewResult.findViewById(R.id.otherMessageTime)
            viewResult!!.tag = holder

            holder.bodyMessage.text = messageThat.text
            holder.time.text = MyUtils().convertLongToTime(messageThat.time, "HH:mm")
        }

        return viewResult

    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    fun add(message: Message) {
        list.add(message)
        notifyDataSetChanged()
    }
}