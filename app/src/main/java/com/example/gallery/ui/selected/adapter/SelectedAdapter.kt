package com.example.gallery.ui.selected.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.databinding.ItemSelectedBinding

class SelectedAdapter : RecyclerView.Adapter<SelectedAdapter.SelectedViewHolder>() {

    private var imageList = arrayListOf<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder {
        return SelectedViewHolder(
            ItemSelectedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
        holder.onBind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addImage(mainImage: ArrayList<Uri>) {
        imageList = mainImage
        notifyDataSetChanged()
    }

    inner class SelectedViewHolder(private var binding: ItemSelectedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(uri: Uri) {
            binding.imageSelectedRecycler.setImageURI(uri)
        }

    }
}