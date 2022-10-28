package com.example.mygithubuser

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.Response.UserResponse
import com.example.mygithubuser.adapter.adapter
import com.example.mygithubuser.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var Adapter : adapter
    private var user = ArrayList<user>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.listMain.setHasFixedSize(true)
        setAdapter()
        viewModel.getSearchUser().observe(this,{
            if (it != null){
                Adapter.set(it)
                loading(false)
            }
        })

    }

    private fun setAdapter(){
        binding.listMain.layoutManager = LinearLayoutManager(this@MainActivity)
        Adapter = adapter()
        Adapter.notifyDataSetChanged()
        Adapter.setOnItemClickCallback(object : adapter.OnItemClickCallback{
            override fun onItemClicked(user: UserResponse) {
                Intent(this@MainActivity,detailActivity::class.java).also {
                    it.putExtra(detailActivity.EXTRA_DATA, user.login)
                    startActivity(it)
                }
            }
        })
        binding.listMain.adapter = Adapter
        binding.ivSearch.setOnClickListener{
            searchUser()
        }
        binding.etSearch.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                searchUser()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun searchUser(){
        val query = binding.etSearch.text.toString()
        if (query.isEmpty()) return
        loading(true)
        viewModel.searchUser(query)
    }

    private fun loading(state: Boolean){
        if (state){
            binding.pbMain.visibility = View.VISIBLE
        }else {
            binding.pbMain.visibility = View.GONE
        }
    }
}

