package com.example.chatapp.model.repository.message

import android.os.Handler
import androidx.lifecycle.LiveData
import com.google.firebase.database.*

class FirebaseQueryData : LiveData<DataSnapshot> {
    private lateinit var query: Query
    private val childListener: ChildEventListener = MyEventListener()

    private var listenerRemovePending = false
    private val handler = Handler()

    private val removeListener = Runnable {
        kotlin.run {
            query.removeEventListener(childListener)
            listenerRemovePending = false
        }
    }

    constructor(query: Query) {
        this.query = query
    }

    constructor(db: DatabaseReference) {
        this.query = db
    }

    override fun onActive() {
        if (listenerRemovePending) {
            handler.removeCallbacks(removeListener)
        } else {
            query.addChildEventListener(childListener)
        }
        listenerRemovePending = false
    }

    override fun onInactive() {
        handler.postDelayed(removeListener, 2000)
        listenerRemovePending = true
    }

    private inner class MyEventListener : ChildEventListener {
        override fun onCancelled(p0: DatabaseError) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        }

        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onChildAdded(p0: DataSnapshot, p1: String?) {
            value = p0
        }

        override fun onChildRemoved(p0: DataSnapshot) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

}