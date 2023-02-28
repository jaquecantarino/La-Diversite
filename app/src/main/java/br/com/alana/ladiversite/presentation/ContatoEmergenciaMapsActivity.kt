package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import br.com.alana.ladiversite.databinding.ActivityContatoEmergenciaMapsBinding


class ContatoEmergenciaMapsActivity : AppCompatActivity() {

    private val binding: ActivityContatoEmergenciaMapsBinding by lazy {
        ActivityContatoEmergenciaMapsBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //seta FULLSCREEN
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //binding.cardviewCall.setOnClickListener { Discador.ligarTelefone("015"+getTelefoneContato(), this, this@ContatoEmergenciaMapsActivity) }
    }

    companion object {

        fun startMapEmergencia(context: Context): Intent {
            return Intent(context, ContatoEmergenciaMapsActivity::class.java)
        }
    }
}