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

        getDadosAcolhida()

        binding.cardDetalheAcolhida.setOnClickListener{
            val tel = binding.telefoneDetalhe.text.toString()
            if (tel.isNotEmpty())
                Discador.ligarTelefone(tel, this)
        }
    }

    private fun getDadosAcolhida(){
        binding.nomeCasaDetalhe.text = intent.getStringExtra("nomeCasa")
        binding.enderecoDetalheLabel.text = intent.getStringExtra("endereco")
        binding.publicoAlvoDetalhe.text = intent.getStringExtra("publico")
        binding.telefoneDetalhe.text = intent.getStringExtra("telefone")
        binding.emailDetalhe.text = intent.getStringExtra("email")
        binding.servicosDetalhe.text = intent.getStringExtra("servico")
    }

    companion object {
        fun startAcolhidaDetalhe(context: Context,
                                 nomeCasa: String?,
                                 endereco: String?,
                                 publico: String?,
                                 telefone: String?,
                                 email: String?,
                                 servico: String?): Intent {
            val intentDetalhes = Intent(context, AcolhidaDetalheActivity::class.java)
            intentDetalhes.apply {
                putExtra("nomeCasa", nomeCasa)
                putExtra("endereco", endereco)
                putExtra("publico", publico)
                putExtra("telefone", telefone)
                putExtra("email", email)
                putExtra("servico", servico)
            }
            return intentDetalhes
        }
    }
}