package com.example.kakaotalk_dms.model

class ChatContent:ChatEntity{
    var content:String
    var isMyChat:Boolean = false

    constructor(content:String){
        this.content = content
        isMyChat = false
    }
    constructor(content: String, myChat:Boolean){
        this.content = content
        this.isMyChat = myChat
    }
    override fun toString():String = content
    override fun isMine():Boolean = isMyChat
}