package com.shahzaib.phone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahzaib.phone.CallLog
import com.shahzaib.phone.R

class CallLogsListAdapter(private var list: ArrayList<CallLog>, private val context: Context) :
    RecyclerView.Adapter<CallLogsListAdapter.CustomViewHolder>() {

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.callLogName)
        val description: TextView = view.findViewById(R.id.callLogDescription)
        val photo: ImageView = view.findViewById(R.id.callLogImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.call_logs_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.description.text = list[position].description
        if (list[position].photo != null)
            holder.photo.setImageBitmap(list[position].photo)
    }

    override fun getItemCount(): Int = list.size
}