package com.example.qrcodegenaratorapp

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edText = findViewById<EditText>(R.id.ed)
        val qrCode = findViewById<ImageView>(R.id.qrImage)
        val btn = findViewById<Button>(R.id.btnSubmit)
        val size = 512

        btn.setOnClickListener {
            val value = edText.text.toString()
            if(value.isEmpty())
                Toast.makeText(this, "Enter value for qr", Toast.LENGTH_SHORT).show()
            else{
                val bits = QRCodeWriter().encode(value,BarcodeFormat.QR_CODE,size,size)
                val bitMap = Bitmap.createBitmap(bits.width,bits.height,Bitmap.Config.RGB_565)
                for(i in 0 until bits.width){
                    for (j in 0 until bits.height){
                        var color = Color.WHITE
                        if(bits.get(i,j))
                            color = Color.BLACK
                        bitMap.setPixel(i,j,color)
                    }
                    qrCode.setImageBitmap(bitMap)
                }
            }
        }
    }
}