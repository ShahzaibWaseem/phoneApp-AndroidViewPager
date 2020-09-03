package com.shahzaib.phone

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.shahzaib.phone.databinding.ViewPagerActivityBinding

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ViewPagerActivityBinding
    private lateinit var mAdapter: ViewPagerAdapter
    private lateinit var tabLayoutMediator: TabLayoutMediator

    private var contactList: ArrayList<Contact> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewPagerActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), CONTACTS_REQUEST_CODE)
        else
            readContacts()

        Log.i("Contacts", contactList.toString())
        mAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            if (position == 0){
                tab.text = "Call Logs"
                mAdapter.createFragment(position)
            }
            else if (position == 1){
                tab.text = "Contacts"
                mAdapter.createFragment(position, contactList)
            }
        }

        binding.pager.adapter = mAdapter
        tabLayoutMediator.attach()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CONTACTS_REQUEST_CODE)
            readContacts()
    }

    private fun readContacts() {
        Log.i("readContacts", "Reading Contacts")
        val contentResolver = contentResolver
        val nameCursor: Cursor? = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null)
        if (nameCursor!!.moveToFirst()) {
            do {
                val id: String = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name: String = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                var number: String = ""
                if (nameCursor.getInt(nameCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0){
                    val numberCursor: Cursor? = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
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

    companion object {
        private const val CONTACTS_REQUEST_CODE = 1
    }

//    PHOTO_THUMBNAIL_URI, NUMBER, NORMALIZED_NUMBER
}