package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alana.ladiversite.databinding.ActivityCadastroAcolhidaBinding

class CadastroAcolhidaActivity : AppCompatActivity() {

    private val binding: ActivityCadastroAcolhidaBinding by lazy {
        ActivityCadastroAcolhidaBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        startActivity(HomeActivity.startHome(this).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    companion object {
        fun startCadastroAcolhida(context: Context): Intent {
            return Intent(context, CadastroAcolhidaActivity::class.java)
        }
    }

}