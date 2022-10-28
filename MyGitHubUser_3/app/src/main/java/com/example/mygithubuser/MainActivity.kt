package com.example.mygithubuser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.Response.UserResponse
import com.example.mygithubuser.adapter.Adapter
import com.example.mygithubuser.databinding.ActivityMainBinding
import com.example.mygithubuser.datastore.Preferences
import com.example.mygithubuser.theme.ThemeActivity
import com.example.mygithubuser.theme.ThemeViewModel


private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore("setting")
class MainActivity : AppCompatActivity() {

    private lateinit var Adapter : Adapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var themeViewModel: ThemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        val pref = Preferences.getInstance(dataStore)
        themeViewModel = ViewModelProvider(this, ThemeViewModel.Factory(pref)).get(ThemeViewModel::class.java)

        binding.listMain.setHasFixedSize(true)
        setAdapter()
        viewModel.getSearchUser().observe(this,{
            if (it != null){
                Adapter.set(it)
                loading(false)
            }
        })

        themeViewModel.getTheme().observe(this){
            if (it){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


    }

    private fun setAdapter(){
        binding.listMain.layoutManager = LinearLayoutManager(this@MainActivity)
        Adapter = Adapter()
        Adapter.notifyDataSetChanged()
        Adapter.setOnItemClickCallback(object : Adapter.OnItemClickCallback{
            override fun onItemClicked(user: UserResponse) {
                Intent(this@MainActivity,DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_DATA, user.login)
                    it.putExtra(DetailActivity.EXTRA_ID, user.id)
                    it.putExtra(DetailActivity.EXTRA_AVATAR, user.avatarUrl)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when ( item.itemId){
            R.id.favorite -> {
                Intent(this@MainActivity, FavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.Setting -> {
                Intent(this@MainActivity, ThemeActivity::class.java).also {
                    startActivity(it)
                }

            }
        }
        return super.onOptionsItemSelected(item)
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

