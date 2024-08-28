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
import io.flutter.plugin.common.PluginRegistry

class CredilioSbmPlugin : FlutterPlugin, ActivityAware, MethodChannel.MethodCallHandler, PluginRegistry.ActivityResultListener {
    private lateinit var channel: MethodChannel
    private lateinit var context: Context
    private var activity: FlutterFragmentActivity? = null
    private var pendingResult: MethodChannel.Result? = null

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
                        pendingResult = result
                        val intent = Intent(activity, SbmActivity::class.java)
                        intent.putExtra("token", token)
                        intent.putExtra("url", url)
                        activity?.startActivityForResult(intent, SBM_ACTIVITY_REQUEST_CODE)
                        Log.d("CredilioSbmPlugin", "Sdk Invoking")
                    } catch (e: Exception) {
                        Log.e("CredilioSbmPlugin", "Error starting SbmActivity: ${e.message}")
                        result.error("ACTIVITY_START_ERROR", "Error starting SbmActivity: ${e.message}", null)
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
        activity = binding.activity as? FlutterFragmentActivity
        binding.addActivityResultListener(this)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        activity = null
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        activity = binding.activity as? FlutterFragmentActivity
        binding.addActivityResultListener(this)
    }

    override fun onDetachedFromActivity() {
        activity = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (requestCode == SBM_ACTIVITY_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    pendingResult?.success("SDK operation completed successfully")
                }
                Activity.RESULT_CANCELED -> {
                    pendingResult?.error("SDK_CANCELED", "SDK operation was canceled", null)
                }
                else -> {
                    pendingResult?.error("SDK_ERROR", "SDK operation failed", null)
                }
            }
            pendingResult = null
            return true
        }
        return false
    }

    companion object {
        private const val SBM_ACTIVITY_REQUEST_CODE = 1001
    }
}