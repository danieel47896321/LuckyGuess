package com.example.luckyguess

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.luckyguess.controller.MainController
import com.example.luckyguess.model.MainModel

class MainActivity : AppCompatActivity() {
    private lateinit var status : TextView
    private lateinit var randomNumber : TextView
    private lateinit var pickNumber : TextView
    private lateinit var answer : ImageView
    private lateinit var about : ImageView
    private lateinit var resetGame : Button
    private lateinit var checkGuess : Button
    private lateinit var mainModel: MainModel
    private lateinit var mainController: MainController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init () {
        mainModel = ViewModelProvider(this)[MainModel::class.java]
        mainController = MainController(mainModel, this)
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
    private fun checkAboutButton(){
        about.setOnClickListener{
            mainController.gameRules()
        }
    }
    private fun resetGameButton(){
        resetGame.setOnClickListener{
            mainController.resetGame()
        }
    }
    private fun checkGuessButton(){
        checkGuess.setOnClickListener{
            if(pickNumber.text.isNotEmpty())
                mainController.userGuess(Integer.valueOf(pickNumber.text.toString()))
            else
                mainController.pleasePickNumber()
        }
    }
    fun setStatus(currentStatus: String, number: String){
        status.text = currentStatus
        randomNumber.text = number
    }
    fun setImage(imageID: Int) {
        answer.setImageDrawable(ContextCompat.getDrawable(this,imageID))
    }
    fun showRules(title: String, message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.OK) { _, _ -> }
        builder.setCancelable(false)
        builder.show()
    }
    fun gameOver(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.OK) { _, _ ->
            mainController.resetGame()
        }
        builder.setCancelable(false)
        builder.show()
    }
    fun resetView(empty: String) {
        randomNumber.text = empty
        pickNumber.text = empty
        mainController.setStatus()
        answer.setImageDrawable(null)
    }
    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}