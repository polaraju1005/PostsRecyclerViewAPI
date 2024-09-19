package com.example.apidatarecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apidatarecycler.adapters.ResponseAdapter
import com.example.apidatarecycler.databinding.ActivityMainBinding
import com.example.apidatarecycler.network.RetrofitInstance.getPosts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var responseAdapter: ResponseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        getPosts(this) { posts ->
            CoroutineScope(Dispatchers.Main).launch {
                responseAdapter.submitList(posts)
            }
        }
    }

    private fun setUpRecyclerView() {
        responseAdapter = ResponseAdapter()
        binding.postsRecyclerView.apply {
            adapter = responseAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}