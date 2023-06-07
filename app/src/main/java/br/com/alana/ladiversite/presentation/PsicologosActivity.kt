package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alana.ladiversite.databinding.ActivityPsicologosBinding
import br.com.alana.ladiversite.utils.Utils
import com.example.mobcomponents.customtoast.CustomToast

class PsicologosActivity : AppCompatActivity() {

    private val binding: ActivityPsicologosBinding by lazy { ActivityPsicologosBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@PsicologosActivity)
        setContentView(binding.root)

        initDiscadores()
        initInstaPsicologos()
    }


    private fun initDiscadores() {
        binding.ps1Contato.setOnClickListener { CustomToast.success(this, "Ligando para Psicólogo...") }
        binding.ps2Contato.setOnClickListener { CustomToast.success(this, "Ligando para Psicólogo...") }
        binding.ps3Contato.setOnClickListener { CustomToast.success(this, "Ligando para Psicólogo...") }
        binding.ps4Contato.setOnClickListener { CustomToast.success(this, "Ligando para Psicólogo...") }
    }

    private fun initInstaPsicologos() {
        binding.ps1Insta.setOnClickListener { Utils.acessarLink("http://www.google.com.br", this) }
        binding.ps2Insta.setOnClickListener { Utils.acessarLink("http://www.google.com.br", this) }
        binding.ps3Insta.setOnClickListener { Utils.acessarLink("http://www.google.com.br", this) }
        binding.ps4Insta.setOnClickListener { Utils.acessarLink("http://www.google.com.br", this) }
    }

    companion object {
        fun startPsico(context: Context): Intent {
            return Intent(context, PsicologosActivity::class.java)
        }
    }
}