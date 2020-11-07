package com.example.prm_pro01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_simulation.*
import kotlin.concurrent.fixedRateTimer

class SimulationActivity : AppCompatActivity() {

    lateinit var p: Person


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)

        p = MainActivity.lista.get(intent.getIntExtra("idPerson",0))

        fnameText.setText(p.firstName)
        lnameText.setText(p.lastName)
        debtText.setText(p.debt.toString())
    }


    fun run_clicked(view: View){
        var speed = speed_input.text.toString().toDouble()
        var com = com_input.text.toString().toInt()
        fixedRateTimer("default", false, 0L, 1000){
            p.debt += p.debt*com
            if (p.debt - speed <= 0){
                p.debt = 0.0
            }
            else {
                p.debt -= speed
            }

            debtText.setText(p.debt.toString())
            if (p.debt - speed <= 0) cancel()
        }
    }

}
