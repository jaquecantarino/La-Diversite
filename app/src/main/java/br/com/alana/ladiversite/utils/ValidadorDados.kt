package br.com.alana.ladiversite.utils

import android.text.TextUtils
import android.util.Patterns

class ValidadorDados {
    companion object{
        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidSenha(senha: String): Boolean{
            return !TextUtils.isEmpty(senha) && senha.length >5
        }
    }
}