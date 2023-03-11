package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityUsuarioCadastroBinding
import br.com.alana.ladiversite.utils.ValidadorDados
import com.example.mobcomponents.customtoast.CustomToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UsuarioCadastroActivity : AppCompatActivity() {

    private val binding: ActivityUsuarioCadastroBinding by lazy { ActivityUsuarioCadastroBinding.inflate(layoutInflater) }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.cadastrarBtn.setOnClickListener {
           cadastrarUser()
        }

    }
    private fun efetuaCadastro(email: String, senha: String ){
        binding.cadastrarBtn.isEnabled = false
        if (!ValidadorDados.isValidEmail(email)){
            binding.emailCadastro.error = getText(R.string.email_valid)
            binding.cadastrarBtn.isEnabled = true
        } else if (!ValidadorDados.isValidSenha(senha)){
            binding.senhaCadastro.error = getText(R.string.senha_valid)
            binding.cadastrarBtn.isEnabled = true
        } else {
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this){
                    task ->
                if (task.isSuccessful){
                    binding.animCad.visibility = View.VISIBLE
                    CustomToast.success(this, getString(R.string.cadastro_ok))
                    delay()
                } else {
                    CustomToast.success(this, getString(R.string.cadastro_nok))
                    clearFields()
                    binding.cadastrarBtn.isEnabled = true
                }
            }
        }
    }

    private fun cadastrarUser(){
        if (TextUtils.isEmpty(binding.emailCadastro.text)){
            binding.emailCadastro.error = getText(R.string.email_obrigatorio)
            binding.cadastrarBtn.isEnabled = true
        } else if (TextUtils.isEmpty(binding.senhaCadastro.text)) {
            binding.senhaCadastro.error = getText(R.string.senha_obrigatorio)
            binding.cadastrarBtn.isEnabled = true
        } else {
            efetuaCadastro(binding.emailCadastro.text.toString(),
                binding.senhaCadastro.text.toString())
        }
    }

    private fun delay(){
        Handler().postDelayed({
            finish()
        }, 2000)
    }

    private fun clearFields(){
        binding.emailCadastro.text.clear()
        binding.senhaCadastro.text.clear()
    }

    companion object {
        fun startCadastroUser(context: Context): Intent {
            return Intent(context, UsuarioCadastroActivity::class.java)
        }
    }
}