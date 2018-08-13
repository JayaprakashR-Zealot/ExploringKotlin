package com.example.learnkotlin

import android.arch.persistence.room.Room
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.learnkotlin.fragment.FragmentScreenOne
import com.example.learnkotlin.fragment.FragmentScreenTwo
import com.example.learnkotlin.fragment.FragmentThree
import com.example.learnkotlin.room_database.User
import com.example.learnkotlin.room_database.UserDatabase


class ViewPagerActivity : AppCompatActivity() {
    private val TAG: String = "ViewPagerActivity"
    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var userDatabase: UserDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        viewPager = findViewById<ViewPager>(R.id.viewpager)
        setupViewPager(viewPager!!)

        tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout!!.setupWithViewPager(viewPager)

        // Room DB framework
        userDatabase = Room.databaseBuilder(applicationContext,
                UserDatabase::class.java, "user-db").build()
        DatabaseAsync().execute()
    }

    private inner class DatabaseAsync : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            userDatabase?.userDAOAccess()?.insert(User(1, "Steve", "Jobs", "Photo 1"))
            return null
        }

        override fun onPostExecute(aVoid: Void) {
            super.onPostExecute(aVoid)
            Log.i(TAG, "Added user:" + userDatabase?.userDAOAccess()!!.getAllUsers())
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentScreenOne(), "ONE")
        adapter.addFragment(FragmentScreenTwo(), "TWO")
        adapter.addFragment(FragmentThree(), "THREE")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }


        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }
}
