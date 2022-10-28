package com.example.mygithubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser.databinding.GitUserBinding
import com.example.mygithubuser.user


class adapter(private var listUser : ArrayList<user>) : RecyclerView.Adapter<adapter.ListViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(GitUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.apply {
            holder.itemView.setOnClickListener{
                onItemClickCallback.onItemClicked(listUser[position])
            }
            gitname.text = listUser[position].name
            gitusername.text = listUser[position].username
            company.text = listUser[position].company
            Glide.with(holder.itemView.context)
                .load(listUser[position].photo)
                .circleCrop()
                .into(holder.binding.ppUser)
        }
    }

    override fun getItemCount(): Int = listUser.size

    class ListViewHolder(val binding: GitUserBinding) : RecyclerView.ViewHolder(binding.root)
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
}


interface OnItemClickCallback {
    fun onItemClicked(user: user)

}


