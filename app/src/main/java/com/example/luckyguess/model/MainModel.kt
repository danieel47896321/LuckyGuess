package com.example.luckyguess.model

import androidx.lifecycle.ViewModel

class MainModel: ViewModel() {
    var round: Int = 0
    var goodGuesses: Int = 0
    var badGuesses: Int = 0
    var randNumber : Int = -1
    var image: Int = -1
}