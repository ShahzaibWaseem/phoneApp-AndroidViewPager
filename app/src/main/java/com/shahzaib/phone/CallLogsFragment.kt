package com.shahzaib.phone

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shahzaib.phone.adapters.CallLogsListAdapter
import com.shahzaib.phone.databinding.CallLogsFragmentBinding

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

    fun readCallLogs() {

    }
}