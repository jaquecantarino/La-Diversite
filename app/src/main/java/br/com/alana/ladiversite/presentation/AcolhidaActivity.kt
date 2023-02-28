package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.alana.ladiversite.databinding.ActivityAcolhidaBinding
class AcolhidaActivity : AppCompatActivity() {

    private val binding: ActivityAcolhidaBinding by lazy {
        ActivityAcolhidaBinding.inflate(
            layoutInflater
        )
    }
    private val recycler: RecyclerView by lazy { binding.recyclerAcolhida }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(binding.root)

        binding.cadastroCasa.setOnClickListener {
            startActivity(CadastroAcolhidaActivity.startCadastroAcolhida(this))
        }

        binding.aprovarBtn.setOnClickListener {
            startActivity(AdminActivity.startAdmin(this))
        }
    }

    companion object {
        fun startAcolhida(context: Context): Intent {
            return Intent(context, AcolhidaActivity::class.java)
        }
    }
}

