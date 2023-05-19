package br.com.alana.ladiversite.utils

import android.text.TextUtils
import android.util.Patterns

class ValidadorDados {
    companion object{
        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isPhoneValid(telefone: String): Boolean {
            return !TextUtils.isEmpty(telefone) && Patterns.PHONE.matcher(telefone).matches() && telefone.length >= 14 && telefone.length <= 15
        }

        fun isValidEmailOnly(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidSenha(senha: String): Boolean{
            return !TextUtils.isEmpty(senha) && senha.length >5
        }

    }
}