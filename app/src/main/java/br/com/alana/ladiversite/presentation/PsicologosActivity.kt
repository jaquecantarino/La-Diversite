package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import br.com.alana.ladiversite.databinding.ActivityPsicologosBinding

class PsicologosActivity : AppCompatActivity() {

    private val binding: ActivityPsicologosBinding by lazy { ActivityPsicologosBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        binding.cardPsico1.setOnClickListener {  }
        binding.cardPsico2.setOnClickListener {  }
        binding.cardPsico3.setOnClickListener {  }
    }
    companion object {
        fun startPsico(context: Context): Intent {
            return Intent(context, PsicologosActivity::class.java)
        }
    }
}