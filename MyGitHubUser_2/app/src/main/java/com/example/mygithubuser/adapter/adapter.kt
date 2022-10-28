package com.example.mygithubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser.Response.UserResponse
import com.example.mygithubuser.databinding.GitUserBinding


class adapter() : RecyclerView.Adapter<adapter.ListViewHolder>() {

    private val listUser = ArrayList<UserResponse>()

    private var onItemClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(GitUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }
    override fun getItemCount(): Int = listUser.size
    
    inner class ListViewHolder(val binding: GitUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserResponse){
            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(user)
            }

            Glide.with(itemView)
                .load(user.avatarUrl)
                .centerCrop()
                .circleCrop()
                .into(binding.ppUser)
            binding.gitname.text = user.login
        }
    }
    
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    fun set(user: ArrayList<UserResponse>){
        listUser.clear()
        listUser.addAll(user)
        notifyDataSetChanged()
    }
    interface OnItemClickCallback {
        fun onItemClicked(user: UserResponse)

    }

}




