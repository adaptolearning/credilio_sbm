package com.example.crediliosbm

import androidx.annotation.NonNull
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import `in`.co.sbmbank.library.PartnerLibrary
import `in`.co.sbmbank.library.PartnerLibrarySingleton
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

// Import your Partner Library classes here

class CrediliosbmPlugin : FlutterPlugin, MethodCallHandler {
  private lateinit var channel: MethodChannel
  private lateinit var context: Context

  private lateinit var library: PartnerLibrary
  lateinit var callback: ActivityResultLauncher<Intent>

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "crediliosbm")
    channel.setMethodCallHandler(this)
    context = flutterPluginBinding.applicationContext

  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    when (call.method) {
      "initPartnerLibrary" -> {
        // Initialize the SDK
        PartnerLibrarySingleton.init("https://sbmsmartbankinguat.esbeeyem.com:9443")
        library = PartnerLibrarySingleton.instance



      }
      "openSdkActivity" -> {
        val token = call.argument<String>("token")
        val endpoint = call.argument<String>("endpoint")

        if (token != null &&  endpoint!=null) {

          library.open(context, token, endpoint, callback)
          result.success("Opening SDK activity for endpoint: $endpoint")
        } else {
          result.error("PARAMS_ERROR", "Token or endpoint parameter missing", null)
        }
      }
      else -> result.notImplemented()
    }
  }

  private fun handleSdkResult(resultCode: Int) {
    // Handle SDK result if needed
    // Example: Implement handling of SDK result callback
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
