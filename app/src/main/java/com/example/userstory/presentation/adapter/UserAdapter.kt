package com.example.userstory.presentation.adapter

import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userstory.databinding.ItemUserBinding
import com.example.userstory.domain.model.User
import com.example.userstory.presentation.utils.ImageLoader

class UserAdapter(private val onItemClicked: (User?) -> Unit):  PagingDataAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback())  {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item: User? = getItem(position)
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onItemClicked(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    inner class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: User?){
            ImageLoader.loadAvatar(
                requestManager =  Glide.with(binding.root.context),
                url = item?.avatarUrl,
                target = binding.ivUserAvatar
            )
            binding.tvUserName.text = item?.login
            binding.tvLink.isClickable = true
            binding.tvLink.movementMethod = LinkMovementMethod.getInstance()
            val text = "<a href='${item?.htmlUrl}'> ${item?.htmlUrl} </a>"
            binding.tvLink.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean{
        return oldItem.login == newItem.login
    }
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.avatarUrl == newItem.avatarUrl
                && oldItem.htmlUrl == newItem.htmlUrl
    }
}

