package com.example.mygithubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.Response.UserFavorite
import com.example.mygithubuser.adapter.AdapterFavorite
import com.example.mygithubuser.databinding.ActivityFavoriteBinding
import com.example.mygithubuser.local.FavoriteEntity

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var adapter: AdapterFavorite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AdapterFavorite()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        adapter.setOnItemClickCallback(object : AdapterFavorite.OnItemClickCallback{
            override fun onItemClicked(user: UserFavorite) {
                Intent(this@FavoriteActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_DATA, user.login)
                    it.putExtra(DetailActivity.EXTRA_AVATAR, user.avatarUrl)
                    it.putExtra(DetailActivity.EXTRA_ID, user.id)
                    startActivity(it)
                }
            }
        })

        binding.rvFavorite.setHasFixedSize(true)
        binding.rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        binding.rvFavorite.adapter = adapter

        viewModel.getFavorite().observe(this, {
            if (it != null){
                val favList = listFav(it)
                adapter.setFav(favList)

            }
        })


    }
    fun listFav(user: List<FavoriteEntity>): ArrayList<UserFavorite>{
        val listFav = ArrayList<UserFavorite>()

        for (users in user){
            val listUser  = UserFavorite(users.login, users.id, users.avatar)
            listFav.add(listUser)

        }
        return listFav
    }


}