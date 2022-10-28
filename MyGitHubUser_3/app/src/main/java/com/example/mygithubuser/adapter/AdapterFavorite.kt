package com.example.mygithubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser.Response.UserFavorite
import com.example.mygithubuser.databinding.GitUserBinding

class AdapterFavorite() : RecyclerView.Adapter<AdapterFavorite.ListViewHolder>() {

    private val listUserFav = ArrayList<UserFavorite>()

    private var onItemClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFavorite.ListViewHolder {
        return ListViewHolder(
            GitUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUserFav[position])
    }

    override fun getItemCount(): Int = listUserFav.size

    inner class ListViewHolder(val binding: GitUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserFavorite) {
            binding.root.setOnClickListener {
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

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setFav(user: ArrayList<UserFavorite>) {
        listUserFav.clear()
        listUserFav.addAll(user)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: UserFavorite)
    }
}
