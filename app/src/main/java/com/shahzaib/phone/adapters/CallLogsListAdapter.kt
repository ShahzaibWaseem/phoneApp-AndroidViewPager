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
        val number: TextView = view.findViewById(R.id.callLogNumber)
        val date: TextView = view.findViewById(R.id.callLogDate)
        val time: TextView = view.findViewById(R.id.callLogTime)
        val callTypeIcon: ImageView = view.findViewById(R.id.callTypeIcon)
        val photo: ImageView = view.findViewById(R.id.callLogImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.call_logs_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.number.text = list[position].number
        holder.date.text = list[position].date
        holder.time.text = list[position].time
        if (list[position].photo != null)
            holder.photo.setImageBitmap(list[position].photo)
    }

    override fun getItemCount(): Int = list.size
}