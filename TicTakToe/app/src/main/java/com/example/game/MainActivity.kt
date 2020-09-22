package com.example.game

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ActionMenuView
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(view: View) {
        var btnSelected = view as Button
        var cellID = 0
        when (btnSelected.id) {
            R.id.btn1 -> cellID = 1
            R.id.btn2 -> cellID = 2
            R.id.btn3 -> cellID = 3
            R.id.btn4 -> cellID = 4
            R.id.btn5 -> cellID = 5
            R.id.btn6 -> cellID = 6
            R.id.btn7 -> cellID = 7
            R.id.btn8 -> cellID = 8
            R.id.btn9 -> cellID = 9
        }
        Toast.makeText(this, "ID:" + cellID, Toast.LENGTH_LONG).show()
        PlayGame(cellID, btnSelected)
    }

    var Player1 = ArrayList<Int>()
    var Player2 = ArrayList<Int>()
    var activePlayer = 1
    fun PlayGame(cellID: Int, btnSelected: Button) {
        if (activePlayer == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundColor(Color.GREEN)
            Player1.add(cellID)
            activePlayer = 2
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundColor(Color.BLUE)
            Player2.add(cellID)
            activePlayer = 1
        }
        btnSelected.isEnabled = false
        CheckWinner()
    }
    fun CheckWinner(){
        var winner=-1
        if(Player1.contains(1) && Player1.contains(2) && Player1.contains(3)){
            winner=1
        }
        if(Player2.contains(1) && Player2.contains(2) && Player2.contains(3)){
            winner=2
        }

        if(Player1.contains(4) && Player1.contains(5) && Player1.contains(6)){
            winner=1
        }
        if(Player2.contains(4) && Player2.contains(5) && Player2.contains(6)){
            winner=2
        }

        if(Player1.contains(7) && Player1.contains(8) && Player1.contains(9)){
            winner=1
        }
        if(Player2.contains(7) && Player2.contains(8) && Player2.contains(9)){
            winner=2
        }

        if(Player1.contains(1) && Player1.contains(4) && Player1.contains(7)){
            winner=1
        }
        if(Player2.contains(1) && Player2.contains(4) && Player2.contains(7)){
            winner=2
        }
        if(Player1.contains(2) && Player1.contains(5) && Player1.contains(8)){
            winner=1
        }
        if(Player2.contains(2) && Player2.contains(5) && Player2.contains(8)){
            winner=2
        }
        if(Player1.contains(3) && Player1.contains(6) && Player1.contains(9)){
            winner=1
        }
        if(Player2.contains(3) && Player2.contains(6) && Player2.contains(9)){
            winner=2
        }

        if(winner != -1){
            if(winner==1) {
                Toast.makeText(this, "Player1 wins the game", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Player2 wins the game", Toast.LENGTH_LONG).show()
            }
        }
    }
}