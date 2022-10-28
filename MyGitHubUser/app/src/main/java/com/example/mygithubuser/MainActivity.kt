package com.example.mygithubuser

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.adapter.OnItemClickCallback
import com.example.mygithubuser.adapter.adapter
import com.example.mygithubuser.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var Name : Array<String>
    private lateinit var Company : Array<String>
    private lateinit var Location : Array<String>
    private lateinit var Username : Array<String>
    private lateinit var Repository : Array<String>
    private lateinit var Follower : Array<String>
    private lateinit var Following : Array<String>
    private lateinit var Photo : TypedArray
    private lateinit var Adapter : adapter
    private var user = ArrayList<user>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listMain.setHasFixedSize(true)

        res()
        setAdapter()
    }
    private fun setAdapter(){

        binding.listMain.layoutManager = LinearLayoutManager(this)
        Adapter = adapter(user)
        binding.listMain.adapter = Adapter
        Adapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(user: user) {
                val intent = Intent(this@MainActivity, detailActivity::class.java)
                intent.putExtra(detailActivity.EXTRA_DATA, user)
                startActivity(intent)
            }
        })
    }

    private fun res(): ArrayList<user> {
        Name = resources.getStringArray(R.array.name)
        Company = resources.getStringArray(R.array.company)
        Location = resources.getStringArray(R.array.location)
        Username = resources.getStringArray(R.array.username)
        Repository = resources.getStringArray(R.array.repository)
        Follower = resources.getStringArray(R.array.followers)
        Following = resources.getStringArray(R.array.following)
        Photo = resources.obtainTypedArray(R.array.avatar)

        for (position in Name.indices) {
            val users = user(
                Username[position],
                Location[position],
                Name[position],
                Company[position],
                Repository[position],
                Follower[position],
                Following[position],
                Photo.getResourceId(position, 1)
            )
            user.add(users)
        }
        return user
    }
}

