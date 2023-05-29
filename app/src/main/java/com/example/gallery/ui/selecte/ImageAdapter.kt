package com.example.gallery.ui.selecte

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.databinding.ItemImageBinding

class ImageAdapter(private val onCLick: (uri: Uri) -> Unit) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var imageList = arrayListOf<Uri>()
    val selectedList = ArrayList<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(uri: Uri) {
        imageList.addAll(0, listOf(uri))
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(private var binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(uri: Uri) {
            onCLick(uri)
            binding.apply {
                ivFirst.setImageURI(uri)
                itemView.setOnClickListener {
                    if (!ivSelected.isVisible) {
                        ivSelected.isVisible = true
                        selectedList.add(uri)
                    } else {
                        ivSelected.isVisible = false
                        selectedList.remove(uri)
                    }
                }
            }
        }

    }
}