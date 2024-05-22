package com.example.fintrack.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fintrack.R
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
                binding.rbElectricity.isChecked -> R.drawable.ic_electricity
                binding.rbCar.isChecked -> R.drawable.ic_car
                binding.rbCreditCard.isChecked -> R.drawable.ic_credit_card
                binding.rbKey.isChecked -> R.drawable.ic_key
                binding.rbShoppingCart.isChecked -> R.drawable.ic_shopping_cart
                binding.rbWaterDrop.isChecked -> R.drawable.ic_water_drop
                binding.rbHome.isChecked -> R.drawable.ic_home
                binding.rbGameControl.isChecked -> R.drawable.ic_game_control
                binding.rbClothes.isChecked -> R.drawable.ic_clothes
                binding.rbGraphic.isChecked -> R.drawable.ic_graphic
                else -> 0
            }

            if (selectedIcon != 0){
                val resultIntent = Intent().apply {
                    putExtra("selectedIcon", selectedIcon)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}