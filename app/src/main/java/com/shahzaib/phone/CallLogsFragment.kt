package com.shahzaib.phone

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shahzaib.phone.adapters.CallLogsListAdapter
import com.shahzaib.phone.databinding.CallLogsFragmentBinding
import android.provider.CallLog.Calls
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CallLogsFragment: Fragment() {
    private lateinit var binding: CallLogsFragmentBinding
    private lateinit var callLogsAdapter: RecyclerView.Adapter<*>

    private var logsList: ArrayList<CallLog> = arrayListOf()
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CallLogsFragmentBinding.inflate(layoutInflater)
        readCallLogs()
        callLogsAdapter = CallLogsListAdapter(logsList, mContext)

        binding.callLogsRecyclerView.apply {
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
            adapter = callLogsAdapter
        }

        return binding.root
    }

    private fun readCallLogs() {
        Log.i("readContacts", "Reading Contacts")
        val contentResolver = mContext.contentResolver
        val callLogsUri: Uri = Uri.parse("content://call_log/calls")
        val callLogsCursor: Cursor? = contentResolver.query(callLogsUri, null, null, null, Calls.DEFAULT_SORT_ORDER)

        if (callLogsCursor!!.moveToFirst()) {
            do {
                val name: String?= callLogsCursor.getString(callLogsCursor.getColumnIndex(Calls.CACHED_NAME))
                val number: String= callLogsCursor.getString(callLogsCursor.getColumnIndex(Calls.NUMBER))
                val date: Long = callLogsCursor.getLong(callLogsCursor.getColumnIndex(Calls.DATE))
                val callType: Int = Integer.parseInt(callLogsCursor.getString(callLogsCursor.getColumnIndex(Calls.TYPE)))

                val formatter: SimpleDateFormat = SimpleDateFormat("dd/MM/YYYY, hh:mm a")
                val dateString: String = formatter.format(Date(date))


                logsList.add(CallLog(name, number, dateString.split(",")[0],
                    dateString.split(",")[1], callType, null))
            } while (callLogsCursor.moveToNext())
        }
        callLogsCursor.close()
    }
}