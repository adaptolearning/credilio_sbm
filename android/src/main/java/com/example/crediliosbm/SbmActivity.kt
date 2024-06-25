package com.example.crediliosbm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import `in`.co.sbmbank.library.PartnerLibrary
import `in`.co.sbmbank.library.PartnerLibrarySingleton

class SbmActivity : ComponentActivity() {
    private lateinit var library: PartnerLibrary
    private lateinit var callback: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sbm)
        PartnerLibrarySingleton.init("https://sbmsmartbankinguat.esbeeyem.com:9443")
        library = PartnerLibrarySingleton.instance

        // Initialize the ActivityResultLauncher
        callback = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleSdkResult(result.resultCode)
        }

        val token = intent.getStringExtra("Token")

        // Use the token as needed
        if (token != null) {
            library.open(applicationContext, "Token", "/banking/sbm/credit_card/SCC/landing", callback)
        }


    }

    private fun handleSdkResult(resultCode: Int) {
        // Handle SDK result if needed
        // Example: Implement handling of SDK result callback
        Log.d("CrediliosbmPlugin", "SDK Result: $resultCode")
    }
}