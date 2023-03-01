package br.com.alana.ladiversite.presentation

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityLoginBinding
import br.com.alana.ladiversite.utils.ValidadorDados
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        auth = Firebase.auth // inicia a instancia do firebase auth

        binding.loginBtnScreen.setOnClickListener {
            if (TextUtils.isEmpty(binding.emailLogin.text)){
                binding.emailLogin.error = getText(R.string.email_obrigatorio)
            } else if (TextUtils.isEmpty(binding.senhaLogin.text)) {
                binding.senhaLogin.error = getText(R.string.senha_obrigatorio)
            } else {
                doLogin(binding.emailLogin.text.toString(),
                    binding.senhaLogin.text.toString())
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //val currentUser = auth.currentUser
        //updateUI(currentUser)
    }

    fun doLogin(email: String, senha: String){

        if (!ValidadorDados.isValidEmail(email)){
            binding.emailLogin.error = getText(R.string.email_valid)
        } else if (!ValidadorDados.isValidSenha(senha)) {
            binding.senhaLogin.error = getText(R.string.senha_valid)
        } else {
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmailAndPassword:success")
                        Toast.makeText(baseContext, getText(R.string.user_auth_ok), Toast.LENGTH_SHORT).show()
                        startActivity(HomeActivity.startHome(this))
                    } else {
                        Log.w(TAG, "signInWithEmailAndPassword:failure", task.exception)
                        Toast.makeText(baseContext, getText(R.string.user_auth_nok), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    companion object {
        fun startLogin(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }
}