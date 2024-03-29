package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.WindowManager
import androidx.core.location.LocationManagerCompat
import br.com.alana.ladiversite.databinding.ActivityHomeBinding
import br.com.alana.ladiversite.utils.Discador
import br.com.alana.ladiversite.utils.Utils
import com.example.mobcomponents.customtoast.CustomToast

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@HomeActivity)
        setContentView(binding.root)
        preferencias = PreferenceManager.getDefaultSharedPreferences(this)

        binding.acolhidaCardHome.setOnClickListener { startActivity(AcolhidaActivity.startAcolhida(this)) }
        binding.contactEditCardHome.setOnClickListener { startActivity(ContatoEmergenciaActivity.startContatoEmergencia(this)) }
        binding.delegaCardHome.setOnClickListener { startActivity(DelegaciaActivity.startDelegacia(this)) }
        binding.emergencyCardHome.setOnClickListener { Discador.ligarTelefone("190", this, this@HomeActivity) }
        binding.leisCardHome.setOnClickListener { startActivity(LeisActivity.startLeis(this)) }
        binding.psicoCardHome.setOnClickListener { startActivity(PsicologosActivity.startPsico(this)) }

        binding.acionarCardHome.setOnClickListener {
            if (!isLocationEnabled(this)){
                CustomToast.warning(this, "É necessário ligar o serviço de localização antes!")
            } else {
                if (isContatoCadastrado()){
                    startActivity(ContatoEmergenciaMapsActivity.startMapEmergencia(this))
                } else {
                    CustomToast.warning(this, "É necessário cadastrar um contato primeiro!")
                }
            }
        }
    }

    private fun isContatoCadastrado(): Boolean {
        preferencias = getSharedPreferences("contato", MODE_PRIVATE)
        val contato = preferencias.getString("contatoTelefone", "")
        if (contato.isNullOrEmpty()){
            return false
        }
        return true
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return LocationManagerCompat.isLocationEnabled(locationManager)
    }

    companion object {
        fun startHome(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}