package com.example.luckyguess.controller

import com.example.luckyguess.MainActivity
import com.example.luckyguess.R
import com.example.luckyguess.model.MainModel
import kotlin.random.Random

class MainController(private var view: MainActivity, private var mainModel: MainModel) {
    fun setStatus() {
        view.setStatus(getStatus(), getNumber())
        if (mainModel.image != -1 && mainModel.round > 0)
            view.setImage(getImage())
    }
    private fun getStatus(): String {
        return view.resources.getString(R.string.Round)+ " " + mainModel.round + "/10\n" + view.resources.getString(R.string.GoodGuesses)+ " " + mainModel.goodGuesses + "\n" + view.resources.getString(R.string.BadGuesses)+ " " + mainModel.badGuesses + "\n"
    }
    private fun getNumber(): String {
        if (mainModel.randNumber == -1)
            return ""
        return mainModel.randNumber.toString()
    }
    private fun getImage(): Int {
        return mainModel.image
    }
    fun gameRules() {
        view.showRules(getRulesTitle(), getRulesText())
    }

    private fun getRulesTitle(): String {
        return  view.resources.getString(R.string.GameRules)
    }
    private fun getRulesText(): String {
        return  view.resources.getString(R.string.Rules)
    }
    fun getGameOverTitle(): String {
        return  view.resources.getString(R.string.GameOver)
    }
    fun getGameOverText(): String {
        return  view.resources.getString(R.string.GameIsOver)
    }
    fun getSelectNumberText(): String {
        return  view.resources.getString(R.string.PickNumber)
    }
    fun resetGame() {
        mainModel.round = 0
        mainModel.badGuesses = 0
        mainModel.goodGuesses = 0
        mainModel.randNumber = -1
        view.resetView("")
    }
    fun userGuess(pickNumber: Int) {
        if (mainModel.round < 10) {
            mainModel.round++
            mainModel.randNumber = Random.nextInt(1,10)
            if (mainModel.randNumber == pickNumber) {
                mainModel.goodGuesses++
                mainModel.image = R.drawable.correct
            } else {
                mainModel.badGuesses++
                mainModel.image = R.drawable.wrong
            }
            setStatus()
            if (mainModel.round == 10)
                view.gameOver(getGameOverTitle(), getGameOverText())
        }
    }
    fun selectNumber() {
        view.showMessage(getSelectNumberText())
    }
}