package com.shahzaib.phone

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.shahzaib.phone.databinding.ViewPagerActivityBinding

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ViewPagerActivityBinding
    private lateinit var mAdapter: ViewPagerAdapter
    private lateinit var tabLayoutMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewPagerActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), CONTACTS_REQUEST_CODE)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CALL_LOG), CALL_LOGS_REQUEST_CODE)

        mAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.pager.offscreenPageLimit = 2

        tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            if (position == 0){
                tab.text = "Call Logs"
                mAdapter.createFragment(position)
            }
            else if (position == 1){
                tab.text = "Contacts"
                mAdapter.createFragment(position)
            }
        }

        binding.pager.adapter = mAdapter
        tabLayoutMediator.attach()
    }

    companion object {
        private const val CONTACTS_REQUEST_CODE = 1
        private const val CALL_LOGS_REQUEST_CODE = 2
    }
}