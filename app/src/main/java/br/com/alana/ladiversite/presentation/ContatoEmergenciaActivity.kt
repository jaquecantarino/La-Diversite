package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import br.com.alana.ladiversite.databinding.ActivityContatoEmergenciaBinding

class ContatoEmergenciaActivity : AppCompatActivity() {

    private val binding: ActivityContatoEmergenciaBinding by lazy {
        ActivityContatoEmergenciaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

    }

    companion object {
        fun startContatoEmergencia(context: Context): Intent {
            return Intent(context, ContatoEmergenciaActivity::class.java)
        }
    }

}