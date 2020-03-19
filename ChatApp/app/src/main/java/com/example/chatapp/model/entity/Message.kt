package com.example.chatapp.model.entity

class Message {

    private var sendByA: String = ""
    private var textA: String = ""
    private var timeA: Long = 0
    private var loveA: Boolean = false
    private var codeFBA: String = ""

    constructor()

    constructor(
        sendBy: String,
        text: String,
        time: Long,
        love: Boolean
    ) {
        this.sendByA = sendBy
        this.textA = text
        this.timeA = time
        this.loveA = love
    }


    var text: String
        get() {
            return this.textA
        }
        set(value) {
            this.textA = value
        }
    var codeFB: String
        get() {
            return this.codeFBA
        }
        set(value) {
            this.codeFBA = value
        }

    var sendBy: String
        get() {
            return this.sendByA
        }
        set(value) {
            this.sendByA = value
        }

    var time: Long
        get() {
            return this.timeA
        }
        set(value) {
            this.timeA = value
        }

    var love: Boolean
        get() {
            return this.loveA
        }
        set(value) {
            this.loveA = value
        }
}