package com.test.barcodegenerator

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter


class MainActivity : AppCompatActivity() {

    // Layouts are not inflated until onCreateView at runtime
    // You can access such a property without throwing error by using
    // "lateinit", which lets you deter property intialisation
    private lateinit var et: EditText
    private lateinit var bt: Button
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
        * Initialise UI components from XML file
         */
        et = findViewById(R.id.et1)
        bt = findViewById(R.id.btn1)
        img = findViewById(R.id.img1)

        bt.setOnClickListener {
            genBarcode()
        }
    }

    private fun genBarcode() {

        // Geting input value from the EditText
        val inputValue = et.text.toString().trim()

        // Todo: If checkbox selected, set inputValue toUpper()
        inputValue.uppercase()

        if (inputValue.isNotEmpty()) {
            // Initializing a MultiFormatWriter to encode the input value
            val mwriter = MultiFormatWriter()

            try {
                // Generating a barcode matrix
                val matrix = mwriter.encode(inputValue, BarcodeFormat.CODE_128, img.width, img.height)

                // Creating a bitmap to represent the barcode
                val bitmap = Bitmap.createBitmap(img.width, img.height, Bitmap.Config.RGB_565)

                // Iterating through the matrix and set pixels in the bitmap
                for (i in 0 until img.width) {
                    for (j in 0 until img.height) {
                        bitmap.setPixel(i, j, if (matrix[i, j]) Color.BLACK else Color.WHITE)
                    }
                }

                // Seting the bitmap as the image resource of the ImageView
                img.setImageBitmap(bitmap)

            } catch (e: Exception) {

                Toast.makeText(this, "Exception $e", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Showing an error message if the EditText is empty
            et.error = "Please enter a value"
        }

    }

    /**
     * Resources:
     *   1. https://www.geeksforgeeks.org/how-to-generate-barcode-in-android/
     *   2. https://mvnrepository.com/artifact/com.google.zxing/core
     */

}