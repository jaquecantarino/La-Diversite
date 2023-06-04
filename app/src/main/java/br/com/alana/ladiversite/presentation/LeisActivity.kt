package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityLeisBinding
import br.com.alana.ladiversite.utils.Utils

class LeisActivity : AppCompatActivity() {
    private val binding: ActivityLeisBinding by lazy { ActivityLeisBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@LeisActivity)
        setContentView(binding.root)

        initCards()
    }


    private fun initCards() {
        binding.cardMulher.setOnClickListener { startActivity(MulheresLeisActivity.startMulher(this)) }
        binding.cardLgbt.setOnClickListener { startActivity(LgbtLeisActivity.startLgbt(this)) }
        binding.cardPretas.setOnClickListener {
            startActivity(
                PessoasPretasLeisActivity.startPessoasPretas(
                    this
                )
            )
        }
        binding.cardCrimesSex.setOnClickListener {
            startActivity(
                CrimesSexuaisLeisActivity.startCrimesSexuais(
                    this
                )
            )
        }
        binding.cardDireitosApoio.setOnClickListener {
            startActivity(
                DireitosApoioLeisActivity.startDireitosApoio(
                    this
                )
            )
        }

    }

    companion object {
        fun startLeis(context: Context): Intent {
            return Intent(context, LeisActivity::class.java)
        }
    }
}