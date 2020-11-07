package com.example.prm_pro01

import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_adding.*
import kotlinx.android.synthetic.main.item_layout.view.*

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {


            MainActivity.activity.edit_clicked(adapterPosition)


        }

        view.setOnLongClickListener {
            MainActivity.activity.delete_clicked(adapterPosition)
            true
        }


    }

    fun setInfo(id: Int) {
        var person = MainActivity.lista.get(id)
        itemView.people_name.text = "${person.firstName} ${person.lastName}"
        itemView.debt_text.text = person.debt.toString()


    }

}

class MyAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            .let { ItemViewHolder(it) }


    }

    override fun getItemCount(): Int {
        return MainActivity.lista.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setInfo(position)
    }

}