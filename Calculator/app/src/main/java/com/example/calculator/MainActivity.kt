package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun btnNumber(view:  View){
        if(newOp==true){
            editValue.setText("")
        }
        newOp=false
        val btnSelected=view as Button
        var btnClickValue:String=editValue.text.toString()
        when(btnSelected.id){
            btn0.id->{
                btnClickValue+="0"
            }
            btn1.id->{
                btnClickValue+="1"
            }
            btn2.id->{
                btnClickValue+="2"
            }
            btn3.id->{
                btnClickValue+="3"
            }
            btn4.id->{
                btnClickValue+="4"
            }
            btn5.id->{
                btnClickValue+="5"
            }
            btn6.id->{
                btnClickValue+="6"
            }
            btn7.id->{
                btnClickValue+="7"
            }
            btn8.id->{
                btnClickValue+="8"
            }
            btn9.id->{
                btnClickValue+="9"
            }
            btnDot.id->{
                btnClickValue+="."
            }
            btnplusminus.id->{
                btnClickValue="-"+btnClickValue
            }
        }
        editValue.setText(btnClickValue)
    }

    var op="*"
    var oldNumber=""
    var newOp=true
    fun btnOps(view:View) {
        val btnSelected = view as Button
        when (btnSelected.id) {
            btnDiv.id -> {
                op = "/"
            }
            btnMul.id -> {
                op = "*"
            }
            btnSub.id -> {
                op = "-"
            }
            btnAdd.id -> {
                op="+"
            }
        }
        oldNumber=editValue.text.toString()
        newOp=true
    }

    fun btnEqualEvent(view: View){
        var newNo=editValue.text.toString()
        var finalNo:Double?=null
        when(op){
            "*"->{
                finalNo= oldNumber.toDouble() * newNo.toDouble()
            }
            "/"->{
                finalNo= oldNumber.toDouble() / newNo.toDouble()
            }
            "+"->{
                finalNo= oldNumber.toDouble() + newNo.toDouble()
            }
            "-"-> {
                finalNo= oldNumber.toDouble() - newNo.toDouble()
            }
        }
        editValue.setText(finalNo.toString())
        newOp=true
    }

    fun btnPercentEvent(view: View){
        var no=editValue.text.toString().toDouble()/100
        editValue.setText(no.toString())
        newOp=true
    }

    fun btnClearEvent(view: View){
        editValue.setText("0")
        newOp=true
    }
}