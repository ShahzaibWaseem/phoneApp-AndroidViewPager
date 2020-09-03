package com.shahzaib.phone

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shahzaib.phone.adapters.ContactsListAdapter
import com.shahzaib.phone.databinding.ContactsFragmentBinding

class ContactsFragment: Fragment() {
    private lateinit var binding: ContactsFragmentBinding
    private lateinit var contactListAdapter: RecyclerView.Adapter<*>

    private var contactList: ArrayList<Contact> = arrayListOf()
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ContactsFragmentBinding.inflate(layoutInflater)
        readContacts()
        contactListAdapter = ContactsListAdapter(contactList, mContext)

        binding.contactRecyclerView.apply {
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
            adapter = contactListAdapter
        }

        return binding.root
    }

    private fun readContacts() {
        Log.i("readContacts", "Reading Contacts")
        val contentResolver = mContext.contentResolver
        val nameCursor: Cursor? = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null)
        if (nameCursor!!.moveToFirst()) {
            do {
                val id: String = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name: String = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                var number: String = ""
                if (nameCursor.getInt(nameCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0){
                    val numberCursor: Cursor? = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf(id), null)
                    while (numberCursor!!.moveToNext()) {
                        number = numberCursor.getString(numberCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    }
                    numberCursor.close()
                }
                contactList.add(Contact(name, number))
            } while (nameCursor.moveToNext())
        }
        nameCursor.close()
    }
}
