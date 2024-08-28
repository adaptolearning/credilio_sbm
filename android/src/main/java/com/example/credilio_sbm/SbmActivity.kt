import com.example.credilio_sbm
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import `in`.co.sbmbank.library.PartnerLibrary
import `in`.co.sbmbank.library.PartnerLibrarySingleton
import com.example.credilio_sbm.R

class SbmActivity : ComponentActivity() {

    private lateinit var library: PartnerLibrary
    private lateinit var callback: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sbm) // Ensure activity_sbm.xml exists in res/layout

        Log.d("CredilioSbmPlugin", "In SbmActivity")

        PartnerLibrarySingleton.init("https://sbmsmartbankinguat.esbeeyem.com:9443", deviceBindingEnabled = false, whitelistedUrls = arrayOf("razorpay.com"))
        library = PartnerLibrarySingleton.instance

        // Initialize the ActivityResultLauncher
        callback = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleSdkResult(result.resultCode)
        }

        val token = intent.getStringExtra("token")
        val url = intent.getStringExtra("url")

        Log.d("CredilioSbmPlugin", "Token SbmActivity: $token")
        Log.d("CredilioSbmPlugin", "URL SbmActivity: $url")

        if (token != null && url != null) {
            try {
                Log.d("CredilioSbmPlugin", "SDK Invoked")
                library.open(this, token, url, callback)
            } catch (e: Exception) {
                Log.e("CredilioSbmPlugin", "SDK invocation failed: ${e.message}", e)
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        } else {
            Log.e("CredilioSbmPlugin", "Token or URL not found in SbmActivity")
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun handleSdkResult(resultCode: Int) {
        Log.d("SbmActivity", "SDK Result: $resultCode")
        setResult(resultCode)
        finish()
    }
}