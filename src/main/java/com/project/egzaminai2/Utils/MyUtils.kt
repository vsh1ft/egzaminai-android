package com.project.egzaminai2.Utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.project.egzaminai2.R

/**
 * Created by vsh1ft on 14/09/2016.
 */
object MyUtils {

    const val MY_PERMISSIONS_REQUEST_WRITE = 123
    fun isConnection(context: Context): Boolean {
        var haveConnectedWifi = false
        var haveConnectedMobile = false
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.allNetworkInfo
        for (ni in netInfo) {
            if (ni.typeName.equals("WIFI", ignoreCase = true)) if (ni.isConnected) haveConnectedWifi = true
            if (ni.typeName.equals("MOBILE", ignoreCase = true)) if (ni.isConnected) haveConnectedMobile = true
        }
        return haveConnectedWifi || haveConnectedMobile
    }

    fun showInternetWarning(context: Context?) {
        Toast.makeText(context, R.string.no_connection, Toast.LENGTH_LONG).show()
    }

    fun isDatabaseVersionEqual(version: String, context: Activity): Boolean {
        val settings = context.getSharedPreferences("MyPrefsFile", 0)
        return settings.getString("DATABASE_VERSION", "") == version
    }

    fun storeDatabaseVersion(version: String, context: Context) {
        val settings = context.getSharedPreferences("MyPrefsFile", 0)
            settings.edit().putString("DATABASE_VERSION", version).apply()
    }

    @JvmStatic
    fun getExamName(link: String): String {
        var link = link
        val posSlash = link.lastIndexOf("/") + 1
        link = link.substring(posSlash, link.length)
        return link
    }

    /* Checks if external storage is available for read and write */
    val isExternalStorageWritable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return if (Environment.MEDIA_MOUNTED == state) {
                true
            } else false
        }

    /* Checks if external storage is available to at least read */
    val isExternalStorageReadable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return if (Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state) {
                true
            } else false
        }

    //If avaible
    val isExternalStorageAvailable: Boolean
        get() {
            val extStorageState = Environment.getExternalStorageState()
            return if (Environment.MEDIA_MOUNTED == extStorageState) {
                true
            } else false
        }

    fun checkPermission(context: Context?): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (context as Activity?)!!,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                } else {
                    ActivityCompat.requestPermissions(
                        (context as Activity?)!!,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_REQUEST_WRITE
                    )
                }
                false
            } else {
                true
            }
        } else {
            true
        }
    }
}
