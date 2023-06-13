package com.example.qr_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.qr_final.databinding.ActivityScanBinding
import com.google.zxing.integration.android.IntentIntegrator

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private lateinit var scanActivityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scanActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val intentResult = IntentIntegrator.parseActivityResult(result.resultCode, result.data)

            if (intentResult != null) {
                if (intentResult.contents == null) {
                    Toast.makeText(baseContext, "Cancelled", Toast.LENGTH_SHORT).show()
                } else {
                    binding.textContent.text = intentResult.contents
                    binding.textFormat.text = intentResult.formatName
                }
            }
        }

        binding.scanBtn.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setPrompt("Scan a barcode or QR Code")
            intentIntegrator.setOrientationLocked(true)

            scanActivityResultLauncher.launch(intentIntegrator.createScanIntent())
        }
    }
}