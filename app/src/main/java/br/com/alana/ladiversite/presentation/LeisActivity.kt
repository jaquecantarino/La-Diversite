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
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        binding.cardLeis.setOnClickListener{ Utils.acessarLink(getString(R.string.jusbrasil_url), this) }
    }

    companion object {
        fun startLeis(context: Context): Intent {
            return Intent(context, LeisActivity::class.java)
        }
    }
}