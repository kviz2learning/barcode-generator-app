package com.test.barcodegenerator

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.qrcode.QRCodeWriter


class MainActivity : AppCompatActivity() {

    // Layouts are not inflated until onCreateView at runtime
    // You can access such a property without throwing error by using
    // "lateinit", which lets you deter property intialisation
    private lateinit var et: EditText
    private lateinit var bt1: Button
    private lateinit var bt2: Button
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
        * Initialise UI components from XML file
         */
        et = findViewById(R.id.et1)
        bt1 = findViewById(R.id.btn1)
        bt2 = findViewById(R.id.btn2)
        img = findViewById(R.id.img1)

        // TODO:1 Generate multiple encodings with one button click, side scrollable / select tab to choose desired barcode.
        // TODO:2 Add credits page using fragment and navigation bar
        // TODO:3 Create separate QR code reader app
        // TODO:4 Merge both barcode generator and reader apps together
        bt1.setOnClickListener {
            genBarcode()
        }
        bt2.setOnClickListener {
            genQRCode()
        }

    }

    /**
     * There are different types of barcodes. This function encodes text to a CODE_128 barcode type.
     * The type of encoding used depends on several factors, including: type of character (e.g. digits, alpha-
     * numeric), and number of characters.
     *
     * CODE_128 barcodes support any character of the ASCII 128 character set and can store highly diversified information.
     */
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
     * QR code is an example of a 2D barcode.
     *
     *  They are flexible in size, offer a high fault tolerance and have fast readability,
     *  though they can’t be read with a laser scanner. QR codes support four different modes of
     *  data: numeric, alphanumeric, byte/binary, and even Kanji.
     */
    private fun genQRCode() {

        // Geting input value from the EditText
        val inputValue = et.text.toString().trim()

        // Todo: If checkbox selected, set inputValue toUpper()
        inputValue.uppercase()

        if (inputValue.isNotEmpty()) {
            // Initializing a MultiFormatWriter to encode the input value
            val mwriter = MultiFormatWriter()

            try {
                // Generating a QR code matrix
                val matrix = mwriter.encode(inputValue, BarcodeFormat.QR_CODE, img.width, img.height)

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

    private fun decodeBarcode() {

        //TODO: Should be able to detect the encoding type automatically; believe the library should be able to do this

    }

    /**
     * Resources:
     *   1. https://www.geeksforgeeks.org/how-to-generate-barcode-in-android/
     *   2. https://mvnrepository.com/artifact/com.google.zxing/core
     *   3. https://www.scandit.com/resources/guides/types-of-barcodes-choosing-the-right-barcode/
     */

}