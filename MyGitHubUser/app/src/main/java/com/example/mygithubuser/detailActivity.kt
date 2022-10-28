package com.example.mygithubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mygithubuser.databinding.ActivityDetailBinding


class detailActivity : AppCompatActivity() {

        companion object{
        const val  EXTRA_DATA ="0"
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data()



}
    private fun data(){
        val detail = intent.getParcelableExtra<user>(EXTRA_DATA) as user

            binding.detailUsername.text = detail.username
            binding.detailLocation.text = detail.location
            binding.detailName.text = detail.name
            binding.detailCompany.text = detail.company
            binding.detailRepo.text = detail.repository
            binding.detailFollower.text = detail.follower
            binding.detailFollowing.text = detail.following
            binding.detailPp.setImageResource(detail.photo)
        }
    }
