package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import br.com.alana.ladiversite.databinding.ActivityAcolhidaDetalheBinding
import br.com.alana.ladiversite.utils.Discador

class AcolhidaDetalheActivity : AppCompatActivity() {

    private val binding: ActivityAcolhidaDetalheBinding by lazy { ActivityAcolhidaDetalheBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        binding.cardDetalheAcolhida.setOnClickListener{
            val tel = binding.telefoneDetalhe.text.toString()
            if (tel.isNotEmpty())
                Discador.ligarTelefone(tel, this, this@AcolhidaDetalheActivity)
        }
    }

    companion object {
        fun startAcolhidaDetalhe(context: Context,
                                 nomeCasa: String?,
                                 endereco: String?,
                                 publico: String?,
                                 telefone: String?,
                                 email: String?,
                                 acolhimento: String?,
                                 servico: String?): Intent {
            val intentDetalhes = Intent(context, AcolhidaDetalheActivity::class.java)
            intentDetalhes.apply {
                putExtra("nomeCasa", nomeCasa)
                putExtra("endereco", endereco)
                putExtra("publico", publico)
                putExtra("telefone", telefone)
                putExtra("email", email)
                putExtra("acolhimento", acolhimento)
                putExtra("servico", servico)
            }
            return intentDetalhes
        }
    }
}