package dev.roger.pruebatecnica.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import dev.roger.pruebatecnica.R
import dev.roger.pruebatecnica.databinding.ActivityMainBinding
import dev.roger.pruebatecnica.ui.ViewModel.SeriesViewModel
import java.time.Duration
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val seriesViewModel: SeriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        //initViewModel()

        seriesViewModel.onCreate()

        seriesViewModel.seriesList()

        seriesViewModel.seriesModel.observe(this, {
            Toast.makeText(applicationContext,it.original_name,Toast.LENGTH_LONG);
        })

        binding.button.setOnClickListener {
            seriesViewModel.seriesList()
        }

    }

    fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        seriesViewModel.recyclerViewAdapter = SeriesAdapter()
        binding.recyclerView.adapter = seriesViewModel.recyclerViewAdapter
    }
}