package com.example.luckyguess.controller

import androidx.lifecycle.ViewModelProvider
import com.example.luckyguess.MainActivity
import com.example.luckyguess.R
import com.example.luckyguess.model.MainModel
import kotlin.random.Random

class MainController(var view: MainActivity) {
    private var mainModel = ViewModelProvider(view)[MainModel::class.java]
    fun setStatus() {
        view.mainWhenCase(mainModel.statusID)
        if(mainModel.image != -1 && mainModel.round > 0)
            view.mainWhenCase(mainModel.updateImageID)
    }
    fun getImage(): Int { return mainModel.image }
    fun getNumber(): String {
        if(mainModel.randNumber == -1)
            return ""
        return mainModel.randNumber.toString()
    }
    fun getStatus(): String { return view.resources.getString(R.string.Round)+ " " + mainModel.round + "/10\n" + view.resources.getString(R.string.GoodGuesses)+ " " + mainModel.goodGuesses + "\n" + view.resources.getString(R.string.BadGuesses)+ " " + mainModel.badGuesses + "\n" }
    fun getRulesTitle(): String { return  view.resources.getString(R.string.GameRules) }
    fun getRulesText(): String { return  view.resources.getString(R.string.Rules) }
    fun getGameOverTitle(): String { return  view.resources.getString(R.string.GameOver) }
    fun getGameOverText(): String { return  view.resources.getString(R.string.GameIsOver) }
    fun getSelectNumberText(): String { return  view.resources.getString(R.string.PickNumber) }
    fun gameRules() { view.mainWhenCase(mainModel.rulesID) }
    fun resetGame() {
        mainModel.round = 0
        mainModel.badGuesses = 0
        mainModel.goodGuesses = 0
        mainModel.randNumber = -1
        view.mainWhenCase(mainModel.resetID)
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
            if(mainModel.round == 10)
                view.mainWhenCase(mainModel.gameOverID)
        }
    }
    fun selectNumber() { view.mainWhenCase(mainModel.selectNumberID) }
    fun getResetText(): String { return "" }
}