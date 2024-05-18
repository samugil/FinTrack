package com.example.fintrack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.databinding.ActivityCategoryBinding
import com.example.fintrack.databinding.ActivityIconCategoryBinding

class IconCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIconCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIconCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIcon.setOnClickListener {
            val selectedIcon = when {
                binding.rbFuel.isChecked -> R.drawable.ic_gas_station
                binding.rbWifi.isChecked -> R.drawable.ic_wifi
                // Continue with other radio buttons
                else -> 0
            }

            if (selectedIcon != 0) {
                val resultIntent = Intent ()
                resultIntent.putExtra ("selectedIcon", selectedIcon)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}