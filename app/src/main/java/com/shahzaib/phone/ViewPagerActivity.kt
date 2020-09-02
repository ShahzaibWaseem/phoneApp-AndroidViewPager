package com.shahzaib.phone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.shahzaib.phone.databinding.ViewPagerActivityBinding
import kotlinx.android.synthetic.main.view_pager_activity.*
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ViewPagerActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewPagerActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tabLayout.addTab(tabLayout.newTab().setText("Dialer"))
        binding.tabLayout.addTab(tabLayout.newTab().setText("Call Logs"))
        binding.tabLayout.addTab(tabLayout.newTab().setText("Contacts"))



//        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
//
//        }.attach()
    }
}