package com.shahzaib.phone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahzaib.phone.Contact
import com.shahzaib.phone.R

class ContactsListAdapter(private var list: ArrayList<Contact>, private val context: Context) :
    RecyclerView.Adapter<ContactsListAdapter.CustomViewHolder>() {

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.contactName)
        val number: TextView = view.findViewById(R.id.contactNumber)
        val photo: ImageView = view.findViewById(R.id.contactImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.number.text = list[position].number
        if (list[position].photo != null)
            holder.photo.setImageBitmap(list[position].photo)
    }

    override fun getItemCount(): Int = list.size
}