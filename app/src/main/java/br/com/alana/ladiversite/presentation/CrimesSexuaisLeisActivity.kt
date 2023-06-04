package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alana.ladiversite.databinding.ActivityCrimesSexuaisLeisBinding

class CrimesSexuaisLeisActivity : AppCompatActivity() {

    private val binding: ActivityCrimesSexuaisLeisBinding by lazy { ActivityCrimesSexuaisLeisBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    companion object {
        fun startCrimesSexuais(context: Context): Intent {
            return Intent(context, CrimesSexuaisLeisActivity::class.java)
        }
    }
}