package com.example.crediliosbm

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

import `in`.co.sbmbank.library.PartnerLibrary
import `in`.co.sbmbank.library.PartnerLibrarySingleton

class CrediliosbmPlugin : FlutterPlugin, MethodChannel.MethodCallHandler {

  private lateinit var channel: MethodChannel
  private lateinit var context: Context
  private lateinit var library: PartnerLibrary
  private lateinit var callback: ActivityResultLauncher<Intent>


  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "crediliosbm")
    channel.setMethodCallHandler(this)

    PartnerLibrarySingleton.init("https://sbmsmartbankinguat.esbeeyem.com:9443")
    library = PartnerLibrarySingleton.instance



  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: MethodChannel.Result) {
    when (call.method) {
      "openLibrary" -> {
        val token = call.argument<String>("token")
        Log.d("CrediliosbmPlugin", "Token received: $token")

        if (token != null) {
          library.open(context, token, "/banking/sbm/credit_card/SCC/landing", callback)
          result.success("Opening SDK activity for endpoint")
        } else {
          result.error("PARAMS_ERROR", "Token parameter missing", null)
        }
      }
      else -> result.notImplemented()
    }
  }

  private fun handleSdkResult(resultCode: Int) {
    // Handle SDK result if needed
    // Example: Implement handling of SDK result callback
    Log.d("CrediliosbmPlugin", "SDK Result: $resultCode")
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }


}
