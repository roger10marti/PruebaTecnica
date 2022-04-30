package dev.roger.pruebatecnica.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.roger.pruebatecnica.R
import dev.roger.pruebatecnica.databinding.ActivityMainBinding
import dev.roger.pruebatecnica.databinding.ActivitySerieDetailBinding

class SerieDetail : AppCompatActivity() {
    private lateinit var binding: ActivitySerieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_serie_detail)
        binding = ActivitySerieDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var bundle :Bundle ?=intent.extras

        binding.textSerieName.text = bundle!!.get("pass").toString()
        binding.valorFechaLanzamiento.text = bundle!!.get("passDate").toString()
        binding.valorIdiomaOrigen.text = bundle!!.get("passLang").toString()
        binding.valorMediaVotos.text = bundle!!.get("passAver").toString()
        binding.valorNumeroVotos.text =bundle!!.get("passCount").toString()
        binding.valorResumen.text = bundle!!.get("passResum").toString()

        //binding.textSerieName =
    }
}