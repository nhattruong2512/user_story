package com.example.userstory.presentation.adapter

import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userstory.databinding.ItemUserBinding
import com.example.userstory.domain.model.UserInfo
import com.example.userstory.presentation.utils.ImageLoader


class UserAdapter(
    private var items: MutableList<UserInfo> = arrayListOf(),
    private val onActionClick: (UserInfo) -> Unit
): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: UserInfo){
            ImageLoader.loadAvatar(
                requestManager =  Glide.with(binding.root.context),
                url = item.avatarUrl,
                target = binding.ivUserAvatar
            )
            binding.tvUserName.text = item.login
            binding.tvLink.isClickable = true
            binding.tvLink.movementMethod = LinkMovementMethod.getInstance()
            val text = "<a href='${item.htmlUrl}'> ${item.htmlUrl} </a>"
            binding.tvLink.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onActionClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setData(users: List<UserInfo>){
        items.clear()
        items.addAll(users)
        notifyDataSetChanged()
    }

    fun addUsers(users: List<UserInfo>) {
        val size = items.size
        items.addAll(users)
        notifyItemRangeInserted(size , items.size - 1)
    }
}