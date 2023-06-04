package br.com.alana.ladiversite.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.WindowManager

class Utils {
    companion object {
        fun acessarLink(urlExterna: String, context: Context) {
            val url = Intent(Intent.ACTION_VIEW)
            url.data = Uri.parse(urlExterna)
            context.startActivity(url)
        }

        fun removeNonDigits(tel: String): String {
            val regex = Regex("\\D")
            return regex.replace(tel, "")
        }

        fun setFullScreen(actv: Activity){
            actv.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}