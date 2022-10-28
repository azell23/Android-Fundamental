package com.example.mygithubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mygithubuser.adapter.SectionPager
import com.example.mygithubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var isCheck = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detail = intent.getStringExtra(EXTRA_DATA)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatar = intent.getStringExtra(EXTRA_AVATAR)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val bundle = Bundle()
        bundle.putString(EXTRA_DATA, detail)

        data()
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.check(id)
            withContext(Dispatchers.Main){
                if (count != null){
                    if (count > 0){
                        binding.toggleButton2.isChecked = true
                        isCheck = true
                    } else{
                        binding.toggleButton2.isChecked = false
                        isCheck= false
                    }
                }
            }
        }
        binding.toggleButton2.setOnClickListener{
            isCheck = !isCheck
            if (isCheck){
                viewModel.addFavorite(id, detail.toString(), avatar.toString())
            }else{
                viewModel.delete(id)
            }
            binding.toggleButton2.isChecked = isCheck
        }
    }




    private fun data(){
        val detail = intent.getStringExtra(EXTRA_DATA)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

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
                Glide.with(this@DetailActivity)
                    .load(it.avatarUrl)
                    .centerCrop()
                    .into(binding.detailPp)
            }
        })

        val SectionPager = SectionPager(this)
        binding.vpDetail.adapter = SectionPager
        TabLayoutMediator(binding.tabLayout,binding.vpDetail){ tabLayout, position ->
            tabLayout.text = resources.getString(com.example.mygithubuser.adapter.SectionPager.LayoutTittle[position])
        }.attach()
        }

    companion object{
        const val  EXTRA_DATA ="extra_data"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR ="extra_avatar"
    }
    }

