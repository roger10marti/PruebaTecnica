package dev.roger.pruebatecnica.ui.view

import android.app.ActionBar
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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

        if (checkConnection(applicationContext)) {
            seriesViewModel.onCreate()
        }else{
            seriesViewModel.onCreateWithoutConnection()
        }

        seriesViewModel.seriesList()

        seriesViewModel.seriesModel.observe(this, {
            Toast.makeText(applicationContext,it.original_name,Toast.LENGTH_LONG);
        })

        binding.buttonNext.setOnClickListener {
            if (checkConnection(applicationContext)) {
                seriesViewModel.buttonFunctionality(1,true)
            }else{
                seriesViewModel.buttonFunctionality(1,false)
            }
            seriesViewModel.buttonControl(binding.buttonNext as Button,
                binding.buttonPrevious as Button)
        }

        binding.buttonPrevious.setOnClickListener {
            if (checkConnection(applicationContext)) {
                seriesViewModel.buttonFunctionality(-1,true)
            }else{
                seriesViewModel.buttonFunctionality(-1,false)
            }
            seriesViewModel.buttonControl(binding.buttonNext as Button,
                binding.buttonPrevious as Button)
        }

        binding.imageView?.setOnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
            }
        }

        seriesViewModel.buttonControl(binding.buttonNext as Button,
            binding.buttonPrevious as Button)

        //AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
        //AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);

    }

    fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        seriesViewModel.recyclerViewAdapter = SeriesAdapter()
        binding.recyclerView.adapter = seriesViewModel.recyclerViewAdapter
    }

    fun checkConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connMgr != null) {
            val activeNetworkInfo = connMgr.activeNetwork
            if (activeNetworkInfo != null) { // connected to the internet
                // connected to the mobile provider's data plan
                return true
            }
        }
        return false
    }
}