package com.example.learnkotlin

import android.content.*
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.example.learnkotlin.Constants.Companion.CONNECTON_TIMEOUT_MILLISECONDS
import com.example.learnkotlin.Constants.Companion.READ_TIMEOUT_MILLISECONDS
import com.example.learnkotlin.Constants.Companion.TAG_HOME
import com.example.learnkotlin.adapter.RecyclerAdapter
import com.example.learnkotlin.model.User
import com.example.learnkotlin.service.MyIntentService
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HomeActivity : AppCompatActivity() {

    var prefs: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        prefs = this.getSharedPreferences(Constants.PREF_NAME, 0)

        imgButtonClose.setOnClickListener {
            finish()
        }
        val intent = Intent(this, MyIntentService::class.java)
        intent.action = Constants.FLITER_RECEIVER
        startService(intent)
    }

    val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent?.action) {
                Constants.FLITER_RECEIVER -> updateCount(intent)
            }
        }
    }

    private fun updateCount(intent: Intent) {
        val count = intent.getIntExtra("Count", 0)
        if (0 != count) {
            txtUpdateCount.text = count.toString()
            if (count == 10) {
                txtUpdateCount.visibility = View.GONE
                LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver)
                GetUsersAsyncTask().execute()
            }
        }
    }

    private inner class GetUsersAsyncTask : AsyncTask<String, Void, String>() {
        val progressBar: ProgressBar = this@HomeActivity.progressBarHome
        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: String?): String {
            return getUsers()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (progressBar.isShown) progressBar.visibility = View.GONE
            //btnCallingWebservice.visibility = View.GONE
            Log.i(Constants.TAG_HOME, result)
            var user_data = JSONObject(result).getJSONArray("data")

            val userList: ArrayList<User> = ArrayList()
            for (i in 0..(user_data.length() - 1)) {
                val avater = user_data.getJSONObject(i).get("avatar").toString()
                val fname = user_data.getJSONObject(i).get("first_name").toString()
                val lname = user_data.getJSONObject(i).get("last_name").toString()
                userList.add(User(i, fname, lname, avater))
            }

            if (userList.size > 0) {
                recyclerViewUser.layoutManager = LinearLayoutManager(this@HomeActivity)
                recyclerViewUser.adapter = RecyclerAdapter(userList, this@HomeActivity)
                recyclerViewUser.visibility = View.VISIBLE
                imgButtonClose.visibility = View.VISIBLE
            }

            recyclerViewUser.addOnItemClickListener(object : RecyclerAdapter.OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {
                    /* var user = "Selected : " + userList[position].first_name
                     Toast.makeText(this@HomeActivity,user,Toast.LENGTH_SHORT).show()*/
                }
            })
        }
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: RecyclerAdapter.OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View?) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View?) {
                view?.setOnClickListener({
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                })
            }
        })
    }


    private fun streamToString(inputStream: InputStream): String {
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String
        var result = ""
        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    result += line
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {

        }
        return result
    }

    private fun getUsers(): String {
        var urlConnection: HttpURLConnection? = null

        try {
            val url = URL(Constants.WEBSERVICE_URL)
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = CONNECTON_TIMEOUT_MILLISECONDS
            urlConnection.readTimeout = READ_TIMEOUT_MILLISECONDS

            return streamToString(urlConnection.inputStream)
        } catch (ex: Exception) {
            Log.e(TAG_HOME, "Error:" + ex.localizedMessage)
            return ""
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, IntentFilter(Constants.FLITER_RECEIVER))
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver)
    }
}