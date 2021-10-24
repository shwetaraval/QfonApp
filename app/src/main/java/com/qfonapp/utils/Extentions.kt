package com.qfonapp.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.View


fun View.disableMultiClick() {
    this.isEnabled = false
    Handler(Looper.getMainLooper()).postDelayed({ this@disableMultiClick.isEnabled = true }, 1500)
}

fun isNotEmpty(str: CharSequence?): Boolean {
    return !(str == null || str.isEmpty() || str == "null" || str == " " || str == "none" || str == "" || str == "(null)")
}

fun openVideoPlayer(context: Context?, uri: String?) {
    uri?.let {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setDataAndType(Uri.parse(uri), "video/*")
        context?.startActivity(intent)
    }
}

fun isNetworkAvailable(context: Context?): Boolean {
    var isConnected = false
    val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeInfo = connMgr?.activeNetworkInfo
    if (activeInfo != null
        && (activeInfo.isConnected || activeInfo.isConnectedOrConnecting)
    ) {
        isConnected = when (activeInfo.type) {
            ConnectivityManager.TYPE_MOBILE ->                     //SLog.i("TYPE_MOBILE : "+ ConnectivityManager.TYPE_MOBILE);
                true
            ConnectivityManager.TYPE_WIFI ->                     //SLog.i("TYPE_WIFI : " + ConnectivityManager.TYPE_WIFI);
                true
            ConnectivityManager.TYPE_WIMAX ->                     //SLog.i("TYPE_WIMAX : " + ConnectivityManager.TYPE_WIMAX);
                true
            ConnectivityManager.TYPE_ETHERNET ->                     //SLog.i("TYPE_ETHERNET : "+ ConnectivityManager.TYPE_ETHERNET);
                true
            else -> false
        }
    }
    return isConnected
}