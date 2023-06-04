package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alana.ladiversite.databinding.ActivityMulheresLeisBinding
import android.view.WindowManager
import br.com.alana.ladiversite.utils.Utils

class MulheresLeisActivity : AppCompatActivity() {

    private val binding: ActivityMulheresLeisBinding by lazy { ActivityMulheresLeisBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@MulheresLeisActivity)
        setContentView(binding.root)

        initLinkCards()
    }

    private fun initLinkCards() {
        binding.cardPenha.setOnClickListener { Utils.acessarLink("https://presrepublica.jusbrasil.com.br/legislacao/95552/lei-maria-da-penha-lei-11340-06", this) }
        binding.cardDelito.setOnClickListener { Utils.acessarLink("https://presrepublica.jusbrasil.com.br/legislacao/1033537/lei-12737-12#art-2", this) }
        binding.cardFemini.setOnClickListener { Utils.acessarLink("https://legislacao.presidencia.gov.br/atos/?tipo=LEI&numero=13104&ano=2015&ato=defMTS65UNVpWTacb", this) }
    }

    companion object {
        fun startMulher(context: Context): Intent {
            return Intent(context, MulheresLeisActivity::class.java)
        }
    }
}