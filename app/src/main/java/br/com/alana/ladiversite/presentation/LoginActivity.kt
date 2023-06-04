package br.com.alana.ladiversite.presentation

import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityLoginBinding
import br.com.alana.ladiversite.utils.Utils
import br.com.alana.ladiversite.utils.ValidadorDados
import com.example.mobcomponents.customtoast.CustomToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@LoginActivity)
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

        binding.recuperarSenha.setOnClickListener {
            recuperacaoSenha(it)
        }
    }

    fun recuperacaoSenha(view: View) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Recuperação de senha")
        val dialogLayout = inflater.inflate(R.layout.dialog_senha, null)
        val email = dialogLayout.findViewById<EditText>(R.id.email_cadastrado)
        builder.setView(dialogLayout)
        builder.setPositiveButton("OK") { dialogInterface, i ->
            if (email.length() > 0){
                auth.sendPasswordResetEmail(email.text.toString()).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful){
                        CustomToast.success(this, "Recuperação de senha iniciada, verifique seu e-mail!")
                    } else {
                        CustomToast.error(this,"Erro! Verifique o e-mail digitado!")
                    }
                }    
            } else {
                CustomToast.error(this, "Digite um email válido!")
            }
        }
            .setNegativeButton("Cancelar") {dialog: DialogInterface, which -> dialog.dismiss()}
        builder.show()
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
                        CustomToast.success(this, getString(R.string.user_auth_ok))
                        startActivity(HomeActivity.startHome(this))
                    } else {
                        Log.w(TAG, "signInWithEmailAndPassword:failure", task.exception)
                        CustomToast.error(this, getString(R.string.user_auth_nok))
                    }
                }
        }
    }

    companion object {
        fun startLogin(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}