package com.example.mygithubuser.follow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.MainViewModel
import com.example.mygithubuser.R
import com.example.mygithubuser.adapter.adapter
import com.example.mygithubuser.databinding.FragmentFollowingBinding
import com.example.mygithubuser.detailActivity


class FollowingFragment : Fragment(R.layout.fragment_following) {
    private var _binding : FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var  username : String
    private lateinit var viewModel: MainViewModel
    private lateinit var Adapter :adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = requireActivity().intent.getStringExtra(detailActivity.EXTRA_DATA).toString()
        _binding = FragmentFollowingBinding.bind(view)

        Adapter = adapter()
        Adapter.notifyDataSetChanged()

        binding.rvFollowing.setHasFixedSize(true)
        binding.rvFollowing.layoutManager = LinearLayoutManager(context)
        binding.rvFollowing.adapter = Adapter

        loading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        viewModel.followingUser(username)
        viewModel.getFollowing()
            .observe(viewLifecycleOwner,{
                if (it != null){
                    Adapter.set(it)
                    loading(false)
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun loading(state : Boolean){
        if (state){
            binding.pbFollowing.visibility = View.VISIBLE
        }else{
            binding.pbFollowing.visibility = View.GONE
        }
    }
}