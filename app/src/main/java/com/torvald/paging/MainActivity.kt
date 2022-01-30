package com.torvald.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.torvald.paging.databinding.ActivityMainBinding
import com.torvald.paging.main.CharacterAdapter
import com.torvald.paging.main.CharacterViewModel
import com.torvald.paging.utils.loger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var adapterCharacter:CharacterAdapter

    private val vm:CharacterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()

        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            vm.listData.collect{it ->
                adapterCharacter.submitData(it)
            }
        }
    }

    private fun setupRV() {
        adapterCharacter = CharacterAdapter()
        binding.recyclerView.apply {
            adapter = adapterCharacter

        }
    }
}