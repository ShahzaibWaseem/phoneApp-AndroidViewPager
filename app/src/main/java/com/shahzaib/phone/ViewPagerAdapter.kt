package com.shahzaib.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.io.Serializable

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment = Fragment()
        if (position == 0)
            fragment =  CallLogsFragment()
        else if (position == 1)
            fragment = ContactsFragment()
        return fragment
    }

    fun createFragment(position: Int, contactList: ArrayList<Contact>): Fragment {
        val bundle: Bundle = Bundle()
        val contactsFragment = ContactsFragment()

        bundle.putSerializable("contactList", contactList)
        contactsFragment.arguments = bundle

        return ContactsFragment()
    }
}