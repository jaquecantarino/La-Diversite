package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityDireitosApoioLeisBinding
import br.com.alana.ladiversite.utils.Utils

class DireitosApoioLeisActivity : AppCompatActivity() {

    private val binding: ActivityDireitosApoioLeisBinding by lazy { ActivityDireitosApoioLeisBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@DireitosApoioLeisActivity)
        setContentView(binding.root)
    }

    companion object {
        fun startDireitosApoio(context: Context): Intent {
            return Intent(context, DireitosApoioLeisActivity::class.java)
        }
    }
}