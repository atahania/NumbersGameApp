package com.example.numbersgameapp

import android.content.Context
import android.os.Message
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.ele_raw.view.*
import java.nio.file.Files.size
import java.text.FieldPosition

class MessageAdp (val context: Context, val messages: ArrayList<String>):

    RecyclerView.Adapter<MessageAdp.MessageViewHolder>(){
    class MessageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageAdp.MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.ele_raw,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MessageAdp.MessageViewHolder, position: Int) {
        val Message = messages [position]

        holder.itemView.apply {
            textView1.text = Message
        }
    }
    override fun getItemCount() =messages.size
}