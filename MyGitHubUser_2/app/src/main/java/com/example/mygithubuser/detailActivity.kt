package com.example.mygithubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mygithubuser.adapter.sectionPager
import com.example.mygithubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator


class detailActivity : AppCompatActivity() {

        companion object{
        const val  EXTRA_DATA ="0"
    }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val detail = intent.getStringExtra(EXTRA_DATA)

        val bundle = Bundle()
        bundle.putString(EXTRA_DATA, detail)

        data()

    }
    private fun data(){
        val detail = intent.getStringExtra(EXTRA_DATA)
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        if (detail != null) {
            viewModel.detailUser(detail)
        }
        viewModel.getDetailUser().observe(this, {
            if (it != null){
                binding.detailName.text = it.name
                binding.detailUsername.text = it.login
                binding.detailFollowing.text = it.following.toString()
                binding.detailFollower.text = it.followers.toString()
                binding.detailCompany.text = it.company
                binding.detailRepo.text = it.publicRepos.toString()
                binding.detailLocation.text = it.location
                Glide.with(this@detailActivity)
                    .load(it.avatarUrl)
                    .centerCrop()
                    .into(binding.detailPp)
            }
        })
        val SectionPager = sectionPager(this)
        binding.vpDetail.adapter = SectionPager
        TabLayoutMediator(binding.tabLayout,binding.vpDetail){ tabLayout, position ->
            tabLayout.text = resources.getString(sectionPager.LayoutTittle[position])
        }.attach()
        }
    }
