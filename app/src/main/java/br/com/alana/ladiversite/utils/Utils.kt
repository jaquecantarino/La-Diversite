package br.com.alana.ladiversite.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

class Utils {
    companion object {
        fun acessarLink(urlExterna: String, context: Context) {
            val url = Intent(Intent.ACTION_VIEW)
            url.data = Uri.parse(urlExterna)
            context.startActivity(url)
        }
    }
}