package com.example.luckyguess

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.luckyguess.controller.MainController

class MainActivity : AppCompatActivity() {
    private lateinit var status : TextView
    private lateinit var randomNumber : TextView
    private lateinit var pickNumber : TextView
    private lateinit var answer : ImageView
    private lateinit var about : ImageView
    private lateinit var resetGame : Button
    private lateinit var checkGuess : Button
    private lateinit var mainController: MainController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init () {
        mainController = MainController(this)
        status = findViewById<TextView>(R.id.status)
        randomNumber = findViewById<TextView>(R.id.randomNumber)
        pickNumber = findViewById<TextView>(R.id.pickNumber)
        answer = findViewById<ImageView>(R.id.answer)
        about = findViewById<ImageView>(R.id.about)
        checkGuess = findViewById<Button>(R.id.checkGuess)
        resetGame = findViewById<Button>(R.id.resetGame)
        mainController.setStatus()
        checkAboutButton()
        resetGameButton()
        checkGuessButton()
    }
    fun mainWhenCase(id: String){
        when(id){
            "setStatus" -> { setStatus(mainController.getStatus(), mainController.getNumber()) }
            "setImage" -> { setImage(mainController.getImage()) }
            "showRules" -> { showRules(mainController.getRulesTitle(), mainController.getRulesText()) }
            "gameOver" -> { gameOver(mainController.getGameOverTitle(), mainController.getGameOverText()) }
            "showMessage" -> { showMessage(mainController.getSelectNumberText()) }
            "resetView" -> { resetView(mainController.getResetText()) }
        }
    }
    private fun checkAboutButton(){
        about.setOnClickListener{ mainController.gameRules() }
    }
    private fun resetGameButton(){
        resetGame.setOnClickListener{ mainController.resetGame() }
    }
    private fun checkGuessButton(){
        checkGuess.setOnClickListener{
            if(pickNumber.text.isNotEmpty())
                mainController.userGuess(Integer.valueOf(pickNumber.text.toString()))
            else
                mainController.selectNumber()
        }
    }
    private fun setImage(imageID: Int) { answer.setImageDrawable(ContextCompat.getDrawable(this,imageID)) }
    private fun setStatus(currentStatus: String, number: String){
        status.text = currentStatus
        randomNumber.text = number
    }
    private fun showRules(title: String, text: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(text)
        builder.setPositiveButton(R.string.OK) { _, _ -> }
        builder.setCancelable(false)
        builder.show()
    }
    private fun gameOver(title: String, text: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(text)
        builder.setPositiveButton(R.string.OK) { _, _ ->
            mainController.resetGame()
        }
        builder.setCancelable(false)
        builder.show()
    }
    private fun resetView(text: String) {
        randomNumber.text = text
        pickNumber.text = text
        mainController.setStatus()
        answer.setImageDrawable(null)
    }
    private fun showMessage(message: String) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
}