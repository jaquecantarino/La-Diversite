package br.com.alana.ladiversite.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

class Discador {
    companion object {
        fun ligarTelefone(telefone: String?, context: Context){
            val phone = telefone
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(context, intent, null)
        }
    }
}