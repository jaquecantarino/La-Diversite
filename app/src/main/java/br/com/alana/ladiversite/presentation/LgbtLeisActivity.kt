package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alana.ladiversite.databinding.ActivityLgbtLeisBinding
import br.com.alana.ladiversite.utils.Utils

class LgbtLeisActivity : AppCompatActivity() {

    private val binding: ActivityLgbtLeisBinding by lazy { ActivityLgbtLeisBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@LgbtLeisActivity)
        setContentView(binding.root)

        initLinkCardsLgbt()
    }

    private fun initLinkCardsLgbt() {
        binding.cardAdocao.setOnClickListener { Utils.acessarLink("https://www.jusbrasil.com.br/topicos/10615981/artigo-42-da-lei-n-8069-de-13-de-julho-de-1990", this) }
        binding.cardNomeSocial.setOnClickListener { Utils.acessarLink("http://www.planalto.gov.br/ccivil_03/_ato2015-2018/2016/decreto/D8727.htm", this) }
        binding.cardPenhaTodas.setOnClickListener { Utils.acessarLink("https://presrepublica.jusbrasil.com.br/legislacao/95552/lei-maria-da-penha-lei-11340-06", this) }
        binding.cardHomoTrans.setOnClickListener { Utils.acessarLink("https://presrepublica.jusbrasil.com.br/legislacao/111031/lei-do-crime-racial-lei-7716-89", this) }
        binding.cardTransMental.setOnClickListener { Utils.acessarLink("https://presrepublica.jusbrasil.com.br/legislacao/100810/lei-10216-01#art-1", this) }
        binding.cardJuventude.setOnClickListener { Utils.acessarLink("https://presrepublica.jusbrasil.com.br/legislacao/1035680/lei-12852-13#art-17", this) }
        binding.cardUniao.setOnClickListener { Utils.acessarLink("https://presrepublica.jusbrasil.com.br/legislacao/91577/codigo-civil-lei-10406-02#art-1723", this) }
        binding.cardHomoNaoDoenca.setOnClickListener { Utils.acessarLink("https://site.cfp.org.br/resolucao-01-99/historico/#:~:text=Em%20vigor%20h%C3%A1%2018%20anos,homossexuais%20para%20tratamentos%20n%C3%A3o%20solicitados", this) }
        binding.cardCirurgia.setOnClickListener { Utils.acessarLink("https://bichadajustica.com/blog/lgbtqia-ja-podem-doar-sangue/ ", this) }
    }

    companion object {
        fun startLgbt(context: Context): Intent {
            return Intent(context, LgbtLeisActivity::class.java)
        }
    }
}