package com.example.credilio_sbm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

class CredilioSbmPlugin : FlutterPlugin,ActivityAware, MethodChannel.MethodCallHandler{

  private lateinit var channel: MethodChannel
  private lateinit var context: Context


  private var activity: FlutterFragmentActivity? = null


  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "credilio_sbm")
    channel.setMethodCallHandler(this)

  }



  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: MethodChannel.Result) {
    when (call.method) {
      "openLibrary" -> {
        val token = call.argument<String>("token")
        val url = call.argument<String>("url")
        Log.d("CredilioSbmPlugin", "Token received plugin: $token")
        Log.d("CredilioSbmPlugin", "URL received plugin: $url")

        if (token != null && url != null) {

          try {
                val intent = Intent(activity, SbmActivity::class.java)
                intent.putExtra("token", token)
                intent.putExtra("url", url)
                activity?.startActivity(intent)
                Log.d("CredilioSbmPlugin", "Sdk Invoking")
                result.success("Opening SDK activity for endpoint")
            } catch (e: Exception) {
                Log.e("CredilioSbmPlugin", "Error starting SbmActivity: ${e.message}")
                result.error("ACTIVITY_START_ERROR", "Error starting SbmActivity", null)
            }

        } else {
          Log.d("CredilioSbmPlugin", "Token or Url missing")
          result.error("PARAMS_ERROR", "Token or Url parameter missing", null)
        }
      }
      else -> result.notImplemented()
    }
  }



  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }



  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity as FlutterFragmentActivity

  }

  override fun onDetachedFromActivityForConfigChanges() {
            channel.setMethodCallHandler(null)

  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
            activity = binding.activity as FlutterFragmentActivity?

  }

  override fun onDetachedFromActivity() {
            activity = null

  }

}
