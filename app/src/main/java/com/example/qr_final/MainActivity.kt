package com.example.qr_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qr_final.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toScannerButton.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }
        binding.toCreateButton.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }
}