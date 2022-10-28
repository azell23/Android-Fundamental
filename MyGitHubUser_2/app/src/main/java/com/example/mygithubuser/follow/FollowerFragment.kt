package com.example.mygithubuser.follow

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.MainViewModel
import com.example.mygithubuser.R
import com.example.mygithubuser.adapter.adapter
import com.example.mygithubuser.databinding.FragmentFollowerBinding
import com.example.mygithubuser.detailActivity


class FollowerFragment : Fragment() {

    private  var  _binding : FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var Adapter : adapter
    private lateinit var username:String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = requireActivity().intent.getStringExtra(detailActivity.EXTRA_DATA).toString()
        _binding = FragmentFollowerBinding.bind(view)

        Adapter = adapter()
        Adapter.notifyDataSetChanged()

        binding.rvFollower.setHasFixedSize(true)
        binding.rvFollower.layoutManager = LinearLayoutManager(context)
        binding.rvFollower.adapter = Adapter

        loading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        viewModel.followersUser(username)
        viewModel.getFollowers()
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
        return inflater.inflate(R.layout.fragment_follower, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun loading(state : Boolean){
        if (state){
            binding.pbFollower.visibility = View.VISIBLE
        } else{
            binding.pbFollower.visibility = View.GONE
        }
    }
}