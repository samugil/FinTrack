package com.example.fintrack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fintrack.databinding.ActivityCategoryBinding


class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCriate.setOnClickListener {
        val categoria = binding.tilNewcategory.editText?.text.toString()


        }
    }

}