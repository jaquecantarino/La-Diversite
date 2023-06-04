package br.com.alana.ladiversite.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityApoioEmergenciaBinding
import br.com.alana.ladiversite.utils.Utils

class ApoioEmergenciaActivity : AppCompatActivity() {

    private val binding: ActivityApoioEmergenciaBinding by lazy { ActivityApoioEmergenciaBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@ApoioEmergenciaActivity)
        setContentView(binding.root)
    }
}