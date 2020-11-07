package com.example.prm_pro01

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    companion object{  //static
        var lista: MutableList<Person> = mutableListOf()
        var total: Double = 0.0

        lateinit var activity: MainActivity
    }

    init {
        activity = this
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        totalText.text = total.toString()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter()



    }

    fun addButton_clicked(view: View){
        val intent = Intent(this,AddingActivity::class.java).apply {
            putExtra("mode","add")
        }


        this.startActivityForResult(intent,1)





    }

    fun edit_clicked(id: Int){
        val intent = Intent(this,AddingActivity::class.java).apply {
            putExtra("mode","edit")
            putExtra("idPerson",id)
        }


        this.startActivityForResult(intent,1)
    }

    fun delete_clicked(id: Int){
        /// FOR DELETE ACTION!
        var alert = AlertDialog.Builder(this)
        alert.setMessage("Are you sure you want delete this person?")
        alert.setCancelable(false)
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, which ->
            lista.removeAt(id)
            refresh()

            dialog.cancel()
        })
        alert.setNegativeButton("No", DialogInterface.OnClickListener({ dialog, which ->
            dialog.cancel()

        }))
        alert.setTitle("Please confirm your action")

        alert.show()
    }

    fun refresh(){ //list refresh
        total = 0.0
        for (p: Person in lista){
            total += p.debt
        }
        totalText.text = total.toString()

        recyclerView.adapter?.notifyDataSetChanged()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        refresh()
    }


}
