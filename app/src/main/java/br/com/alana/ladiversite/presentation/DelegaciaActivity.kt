package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import br.com.alana.ladiversite.databinding.ActivityDelegaciaBinding

class DelegaciaActivity : AppCompatActivity() {

    private val binding: ActivityDelegaciaBinding by lazy { ActivityDelegaciaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
    }

    companion object {
        fun startDelegacia(context: Context): Intent {
            return Intent(context, DelegaciaActivity::class.java)
        }
    }
}