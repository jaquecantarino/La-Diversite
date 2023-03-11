package br.com.alana.ladiversite.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import br.com.alana.ladiversite.databinding.ActivityPreLoginBinding
import br.com.alana.ladiversite.utils.Discador

class PreLoginActivity : AppCompatActivity() {

    private val binding: ActivityPreLoginBinding by lazy { ActivityPreLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener { goToLogin() }
        binding.cadastreBtn.setOnClickListener { goToCadastroUser() }
        binding.emergenciaBtn.setOnClickListener { call190() }

    }

    fun goToLogin(){
        startActivity(LoginActivity.startLogin(this))
    }

    fun goToCadastroUser(){
        startActivity(UsuarioCadastroActivity.startCadastroUser(this))
    }

    fun call190(){
        Discador.ligarTelefone("190",this)
    }
}