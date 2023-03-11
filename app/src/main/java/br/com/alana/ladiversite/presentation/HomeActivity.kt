package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import br.com.alana.ladiversite.databinding.ActivityHomeBinding
import br.com.alana.ladiversite.utils.Discador

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        binding.acionarCardHome.setOnClickListener { startActivity(ContatoEmergenciaActivity.startContatoEmergencia(this)) }
        binding.acolhidaCardHome.setOnClickListener { startActivity(AcolhidaActivity.startAcolhida(this)) }
        binding.contactEditCardHome.setOnClickListener { startActivity(ContatoEmergenciaActivity.startContatoEmergencia(this)) }
        binding.delegaCardHome.setOnClickListener { startActivity(DelegaciaActivity.startDelegacia(this)) }
        binding.emergencyCardHome.setOnClickListener { Discador.ligarTelefone("190", this) }
        binding.leisCardHome.setOnClickListener { startActivity(LeisActivity.startLeis(this)) }
        binding.psicoCardHome.setOnClickListener { startActivity(PsicologosActivity.startPsico(this)) }

    }

    companion object {
        fun startHome(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}