package com.example.luckyguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var status : TextView
    private lateinit var randomNumber : TextView
    private lateinit var pickNumber : TextView
    private lateinit var answer : ImageView
    private lateinit var about : ImageView
    private lateinit var resetGame : Button
    private lateinit var checkGuess : Button
    private var round : Int = 0
    private var goodGuesses : Int = 0
    private var badGuesses : Int = 0
    private var randNumber : Int = Random.nextInt(1,10)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init () {
        status = findViewById<TextView>(R.id.status)
        randomNumber = findViewById<TextView>(R.id.randomNumber)
        pickNumber = findViewById<TextView>(R.id.pickNumber)
        answer = findViewById<ImageView>(R.id.answer)
        about = findViewById<ImageView>(R.id.about)
        checkGuess = findViewById<Button>(R.id.checkGuess)
        resetGame = findViewById<Button>(R.id.resetGame)
        setStatus(status)
        checkAboutButton()
        checkGuessButton()
        resetGameButton()
    }
    private fun checkAboutButton(){
        about.setOnClickListener {
            Toast.makeText(this, resources.getString(R.string.Home), Toast.LENGTH_SHORT).show()
        }
    }
    private fun checkGuessButton(){
        checkGuess.setOnClickListener{
            if(round < 10) {
                if(pickNumber.text.isNotEmpty()) {
                    round += 1
                    randNumber = Random.nextInt(1, 10)
                    if (randNumber == Integer.valueOf(pickNumber.text.toString())) {
                        goodGuesses += 1
                        answer.setImageDrawable(resources.getDrawable(R.drawable.correct))
                    } else {
                        badGuesses += 1
                        answer.setImageDrawable(resources.getDrawable(R.drawable.wrong))
                    }
                    randomNumber.text = randNumber.toString()
                    setStatus(status)
                } else{
                    Toast.makeText(this, resources.getString(R.string.PickNumber), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, resources.getString(R.string.GameOver), Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun resetGameButton(){
        resetGame.setOnClickListener {
            round = 0
            goodGuesses = 0
            badGuesses = 0
            randomNumber.text = ""
            pickNumber.text = ""
            setStatus(status)
            answer.setImageDrawable(null)
        }
    }
    private fun setStatus( status : TextView ){
        status.text = resources.getString(R.string.Round) + ": " + round + " / 10\n" +
                resources.getString(R.string.GoodGuesses) + " " + goodGuesses +"\n" +
                resources.getString(R.string.BadGuesses) + " " + badGuesses +"\n"
    }
}