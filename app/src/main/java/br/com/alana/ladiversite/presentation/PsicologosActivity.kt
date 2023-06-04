package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityPsicologosBinding
import br.com.alana.ladiversite.utils.Utils
import com.example.mobcomponents.customtoast.CustomToast

class PsicologosActivity : AppCompatActivity() {

    private val binding: ActivityPsicologosBinding by lazy { ActivityPsicologosBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        binding.cardPsico1.setOnClickListener { Utils.acessarLink(getString(R.string.psico_url1), this) }
        binding.cardPsico2.setOnClickListener { Utils.acessarLink(getString(R.string.psico_url2), this) }
        binding.cardPsico3.setOnClickListener { Utils.acessarLink(getString(R.string.psico_url3), this) }
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
        binding.ps1Insta.setOnClickListener { Utils.acessarLink("https://jaquecantarino.github.io/page-ladiversite/", this) }
        binding.ps2Insta.setOnClickListener { Utils.acessarLink("https://jaquecantarino.github.io/page-ladiversite/", this) }
        binding.ps3Insta.setOnClickListener { Utils.acessarLink("https://jaquecantarino.github.io/page-ladiversite/", this) }
        binding.ps4Insta.setOnClickListener { Utils.acessarLink("https://jaquecantarino.github.io/page-ladiversite/", this) }
    }

    companion object {
        fun startPsico(context: Context): Intent {
            return Intent(context, PsicologosActivity::class.java)
        }
    }
}