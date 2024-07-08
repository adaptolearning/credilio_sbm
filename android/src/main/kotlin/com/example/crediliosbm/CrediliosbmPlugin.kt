package com.example.crediliosbm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

class CrediliosbmPlugin : FlutterPlugin,ActivityAware, MethodChannel.MethodCallHandler{

  private lateinit var channel: MethodChannel
  private lateinit var context: Context


  private var activity: FlutterActivity? = null


  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "crediliosbm")
    channel.setMethodCallHandler(this)

  }



  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: MethodChannel.Result) {
    when (call.method) {
      "openLibrary" -> {
        val token = call.argument<String>("token")
        Log.d("CrediliosbmPlugin", "Token received: $token")

        if (token != null) {

          val intent = Intent(context, SbmActivity::class.java)
          intent.putExtra("token", token)
          activity?.startActivity(intent)
          result.success("Opening SDK activity for endpoint")
        } else {
          result.error("PARAMS_ERROR", "Token parameter missing", null)
        }
      }
      else -> result.notImplemented()
    }
  }



  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }



  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity as FlutterActivity

  }

  override fun onDetachedFromActivityForConfigChanges() {
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
  }

  override fun onDetachedFromActivity() {
  }




}
