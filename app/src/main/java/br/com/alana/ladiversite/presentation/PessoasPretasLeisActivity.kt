package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alana.ladiversite.R
import android.view.WindowManager
import br.com.alana.ladiversite.databinding.ActivityPessoasPretasLeisBinding
import br.com.alana.ladiversite.utils.Utils

class PessoasPretasLeisActivity : AppCompatActivity() {

    private val binding: ActivityPessoasPretasLeisBinding by lazy { ActivityPessoasPretasLeisBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@PessoasPretasLeisActivity)
        setContentView(binding.root)

        initLinkCardsPretas()
    }

    private fun initLinkCardsPretas() {
        binding.cardCrimeRacial.setOnClickListener { Utils.acessarLink("https://presrepublica.jusbrasil.com.br/legislacao/111031/lei-do-crime-racial-lei-7716-89#art-1", this) }
        binding.cardPreconceito.setOnClickListener { Utils.acessarLink("https://www.jusbrasil.com.br/topicos/11797094/artigo-20-da-lei-n-7716-de-05-de-janeiro-de-1989", this) }
        binding.cardEstatuto.setOnClickListener { Utils.acessarLink("https://presrepublica.jusbrasil.com.br/legislacao/823981/estatuto-da-igualdade-racial-lei-12288-10", this) }
    }

    companion object {
        fun startPessoasPretas(context: Context): Intent {
            return Intent(context, PessoasPretasLeisActivity::class.java)
        }
    }
}