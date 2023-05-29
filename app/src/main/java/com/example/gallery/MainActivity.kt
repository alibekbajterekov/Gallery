package com.example.gallery

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gallery.databinding.ActivityMainBinding
import com.example.gallery.ui.selecte.ImageAdapter
import com.example.gallery.ui.selected.ResultActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = ImageAdapter(this::onCLickSend)
    private val imageList = arrayListOf<Uri>()

    private val loadImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val image = it.data?.data
                if (image != null) {
                    adapter.addList(image)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        onClick()
    }

    private fun onClick() {
        binding.btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.putExtra(Intent.ACTION_PICK, true)
            loadImage.launch(intent)
        }
    }

    private fun onCLickSend(uri: Uri) {
        binding.tvSend.setOnClickListener {
            imageList.addAll(listOf(uri))
            Intent(this, ResultActivity::class.java).apply {
                putExtra(KEY_IMAGE, adapter.selectedList)
                startActivity(this)
            }
        }
    }

    companion object {
        const val KEY_IMAGE = "image"
    }
}