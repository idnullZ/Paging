package com.torvald.paging.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.torvald.paging.R
import com.torvald.paging.databinding.CharacterLayoutAddBinding
import com.torvald.paging.databinding.CharacterLayoutBinding
import com.torvald.paging.model.RickMorty
import com.torvald.paging.utils.loger
import java.lang.Exception
import kotlin.random.Random

class CharacterAdapter : PagingDataAdapter<RickMorty,
        RecyclerView.ViewHolder>(diffCallback) {

    var onItemClickListener: ((RickMorty) -> Unit)? = null


    var onItemTexeViewClickListener: ((RickMorty) -> Unit)? = null


    inner class ImageViewHolder(val binding: CharacterLayoutBinding) : ViewHolder(binding.root)

    inner class ImageViewHolderPRO(val binding: CharacterLayoutAddBinding) :
        ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<RickMorty>() {
            override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem == newItem
            }
        }

        const val TYPE_BASE = 2
        const val TYPE_PRO = 1

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_BASE -> {
                val bind = CharacterLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ImageViewHolder(bind)
            }
            TYPE_PRO -> {

                val bind = CharacterLayoutAddBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return ImageViewHolderPRO(bind)

            }
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (Random.nextInt() > 0) TYPE_BASE else TYPE_PRO
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currChar = getItem(position)!!
        when (holder) {
            is ImageViewHolder -> {
                holder.itemView.setOnClickListener {
                    onItemClickListener?.invoke(currChar)
                }
                holder.binding.imageView.setOnClickListener {
                    onItemTexeViewClickListener?.invoke(currChar)
                }


                holder.binding.apply {
                    holder.itemView.apply {
                        tvDescription.text = "${currChar?.name}"
                        val imageLink = currChar?.image
                        imageView.load(imageLink) {
                            crossfade(true)
                            crossfade(1000)
                        }
                    }
                }
            }
            is ImageViewHolderPRO -> {


                holder.binding.apply {
                    holder.itemView.apply {
                        tvDescription2.text = "${currChar?.name}"
                        tvNew.text = currChar?.status


                        val imageLink = currChar?.image
                        imageView2.load(imageLink) {
                            crossfade(true)
                            crossfade(1000)
                        }
                    }
                }
            }
        }

    }

}