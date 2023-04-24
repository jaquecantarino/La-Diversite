package br.com.alana.ladiversite.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity

class Discador {
    companion object {
        fun ligarTelefone(telefone: String?, context: Context, activity: Activity){
            val intent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel", telefone, null))

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CALL_PHONE), 1)
                return
            }
            startActivity(context, intent, null)
        }
    }
}