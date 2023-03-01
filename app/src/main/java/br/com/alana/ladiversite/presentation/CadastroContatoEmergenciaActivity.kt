package br.com.alana.ladiversite.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityCadastroContatoEmergenciaBinding

class CadastroContatoEmergenciaActivity : AppCompatActivity() {

    private val binding: ActivityCadastroContatoEmergenciaBinding by lazy { ActivityCadastroContatoEmergenciaBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
    }
}