package com.example.wakeupfriend.data.datasource

data class Alarm(
    val id: Int,
    var phoneNumber: String = "",
    var message: String = "",
    var alarmTime: String = ""
)
