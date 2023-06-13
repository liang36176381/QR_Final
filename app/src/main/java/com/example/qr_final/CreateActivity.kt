package com.example.qr_final

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qr_final.databinding.ActivityCreateBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var toast = Toast.makeText(this, "Your Content Is NULL", Toast.LENGTH_SHORT)
        binding.btnCreate.setOnClickListener {
            val qrCodeContent = binding.qrContent.text.toString()
            if (qrCodeContent == "") {
                toast.cancel()
                toast = Toast.makeText(this, "Your Content Is NULL", Toast.LENGTH_SHORT)
                toast.show()
                return@setOnClickListener
            }
            binding.imgQrCode.setImageBitmap(getQrCodeBitmap(qrCodeContent))
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }

        }
    }

    private fun getQrCodeBitmap(qrCodeContent: String): Bitmap {
        val size = 400
        val bits = QRCodeWriter()
            .encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
            .also {
                for (x in 0 until size)
                    for (y in 0 until size)
                        it.setPixel(
                            x, y,
                            if (bits[x, y])
                                Color.BLACK
                            else
                                Color.WHITE
                        )
            }

    }
}