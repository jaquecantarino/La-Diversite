package br.com.alana.ladiversite.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityCadastroContatoEmergenciaBinding
import br.com.alana.ladiversite.utils.Utils

class CadastroContatoEmergenciaActivity : AppCompatActivity() {

    private val binding: ActivityCadastroContatoEmergenciaBinding by lazy { ActivityCadastroContatoEmergenciaBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@CadastroContatoEmergenciaActivity)
        setContentView(binding.root)
    }
}