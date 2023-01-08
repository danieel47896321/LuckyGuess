package com.example.luckyguess.controller

import com.example.luckyguess.MainActivity
import com.example.luckyguess.R
import com.example.luckyguess.model.MainModel
import kotlin.random.Random

class MainController(var mainModel: MainModel, var view: MainActivity) {
    fun setStatus() {
        val status = view.resources.getString(R.string.Round)+ " " + mainModel.round + "/10\n" +
                     view.resources.getString(R.string.GoodGuesses)+ " " + mainModel.goodGuesses + "\n" +
                     view.resources.getString(R.string.BadGuesses)+ " " + mainModel.badGuesses + "\n"
        var number = mainModel.randNumber.toString()
        if(mainModel.randNumber == -1)
            number = ""
        view.setStatus(status,number)
        if(mainModel.image != -1)
            view.setImage(mainModel.image)
    }
    fun gameRules() {
        val title = view.resources.getString(R.string.GameRules)
        val message = view.resources.getString(R.string.Rules)
        view.showRules(title, message)
    }
    fun resetGame() {
        mainModel.round = 0
        mainModel.badGuesses = 0
        mainModel.goodGuesses = 0
        mainModel.randNumber = -1
        view.resetView("")
    }
    fun userGuess(pickNumber: Int) {
        if(mainModel.round < 10){
            mainModel.round++
            mainModel.randNumber = Random.nextInt(1,10)
            if(mainModel.randNumber == pickNumber){
                mainModel.goodGuesses++
                mainModel.image = R.drawable.correct
            } else{
                mainModel.badGuesses++
                mainModel.image = R.drawable.wrong
            }
            setStatus()
            if(mainModel.round == 10) {
                val title = view.resources.getString(R.string.GameOver)
                val message = view.resources.getString(R.string.GameIsOver)
                view.gameOver(title, message)
            }
        } else{
            val message = view.resources.getString(R.string.GameOver)
            view.showMessage(message)
            resetGame()
        }
    }
    fun pleasePickNumber() {
        val message = view.resources.getString(R.string.PickNumber)
        view.showMessage(message)
    }
}