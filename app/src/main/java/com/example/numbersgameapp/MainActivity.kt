package com.example.numbersgameapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var limro: ConstraintLayout
    private lateinit var guTextText: EditText
    private lateinit var gubutton: Button
    private lateinit var messages: ArrayList<String>
    private lateinit var textView: TextView

    private var answer= 0
    private var guesses= 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        answer = Random.nextInt(10)
        limro = findViewById(R.id.limro)
        messages = ArrayList()
        textView = findViewById(R.id.textView)

        rvMessages.adapter = MessageAdp(this, messages)
        rvMessages.layoutManager = LinearLayoutManager(this)

        guTextText = findViewById(R.id.TextText)
        gubutton = findViewById(R.id.button)

        gubutton.setOnClickListener { addMessage() }
    }

    private fun addMessage() {
        val msg = guTextText.text.toString()
        if (msg.isNotEmpty()) {
            if (guesses > 0) {
                if (msg.toInt() == answer) {
                    disableEntry()
                    showAlertDialog("you win!\n\nPlay again? ")
                } else {
                    guesses--
                    messages.add("you guessed $msg")
                    messages.add("you have $guesses guesses left")
                }
                if (guesses == 0) {
                    disableEntry()
                    messages.add("you lose...\nThe correct answer was $answer.\n\nPlay again?")
                }
            }
            guTextText.text.clear()
            guTextText.clearFocus()
            rvMessages.adapter?.notifyDataSetChanged()
        } else {
            Snackbar.make(limro, "Please enter a number", Snackbar.LENGTH_LONG).show()
        }
    }
    private fun disableEntry() {
        gubutton.isEnabled = false
        gubutton.isClickable = false
        guTextText.isEnabled = false
        guTextText.isClickable = false
    }
    private fun showAlertDialog(title: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(title)
            .setCancelable(false)
            .setPositiveButton("yes", DialogInterface.OnClickListener {
                    dialog, id -> this.recreate()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Game Over")
        alert.show()
    }
}