package com.example.prm_pro01

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_adding.*
import java.lang.Exception

class AddingActivity : AppCompatActivity() {

    var id:Int = 0
    lateinit var person: Person
    lateinit var personCopy: Person


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding)

        if(intent.getStringExtra("mode").equals("add")){
            simButton.isEnabled = false

        }
        else{
            if (intent.hasExtra("idPerson")){
                id = intent.getIntExtra("idPerson",0)
                person = MainActivity.lista.get(id)
                personCopy = Person(person)

                fname_input.setText(person.firstName)
                lname_input.setText(person.lastName)
                debt_input.setText(person.debt.toString())

            }

        }







    }


    fun acceptButton_clicked(view: View){
        var name = fname_input.text
        var lastName = lname_input.text
        var debtString = debt_input.text

        if(!name.isEmpty() && !lastName.isEmpty() && !debtString.isEmpty()){
            try {
                if(intent.getStringExtra("mode").equals("add")) {
                    var debt = debtString.toString().toDouble()
                    var person = Person(name.toString(), lastName.toString(), debt)
                    MainActivity.lista.add(person)
                }
                else{
                    MainActivity.lista.get(id).firstName = fname_input.text.toString()
                    MainActivity.lista.get(id).lastName = lname_input.text.toString()
                    MainActivity.lista.get(id).debt = debt_input.text.toString().toDouble()
                }

                setResult(1)
                finish()



            }catch (ex: Exception){
                var alert = AlertDialog.Builder(this)
                alert.setMessage("Please type correct number")
                alert.setCancelable(false)
                alert.setPositiveButton("OK",DialogInterface.OnClickListener{dialog, which -> dialog.cancel() })

                var a = alert.create()
                a.setTitle("Error")
                a.show()
            }


        }else{
            var alert = AlertDialog.Builder(this)
            alert.setMessage("Please type all information")
            alert.setCancelable(false)
            alert.setPositiveButton("OK",DialogInterface.OnClickListener{dialog, which -> dialog.cancel() })

            var a = alert.create()
            a.setTitle("Error")
            a.show()

        }

    }

    fun declineButton_clicked(view: View){

        var alert = AlertDialog.Builder(this)
        alert.setMessage("Are you sure?")
        alert.setCancelable(false)
        alert.setPositiveButton("Yes",DialogInterface.OnClickListener{dialog, which ->
            fname_input.setText(personCopy.firstName)
            lname_input.setText(personCopy.lastName)
            debt_input.setText(personCopy.debt.toString())
            Toast.makeText(this,"Changes canceled",Toast.LENGTH_SHORT).show()
            dialog.cancel()
        })
        alert.setNegativeButton("No",DialogInterface.OnClickListener({dialog, which ->
            dialog.cancel()

        }))
        alert.setTitle("Please confirm your action")

        alert.show()



    }

    fun simulation_clicked(view: View){
        val intent = Intent(this,SimulationActivity::class.java).apply {
            putExtra("idPerson",id)
        }


        this.startActivity(intent)
    }
}
