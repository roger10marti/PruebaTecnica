package dev.roger.pruebatecnica.ui.view

import android.app.ActionBar
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.roger.pruebatecnica.R
import dev.roger.pruebatecnica.databinding.ActivityMainBinding
import dev.roger.pruebatecnica.ui.ViewModel.SeriesViewModel
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

        binding.buttonNext.setOnClickListener {
            seriesViewModel.buttonNext()
            seriesViewModel.buttonControl(binding.buttonNext,binding.buttonPrevious)
        }

        binding.buttonPrevious.setOnClickListener {
            seriesViewModel.buttonPrevious()
            seriesViewModel.buttonControl(binding.buttonNext,binding.buttonPrevious)
        }

        binding.imageView?.setOnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
            }
        }

        seriesViewModel.buttonControl(binding.buttonNext,binding.buttonPrevious)

        //AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
        //AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);

    }

    fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        seriesViewModel.recyclerViewAdapter = SeriesAdapter()
        binding.recyclerView.adapter = seriesViewModel.recyclerViewAdapter
    }
}