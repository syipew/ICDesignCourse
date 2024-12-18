package com.example.icdesigncourse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuRegister : AppCompatActivity() {
//    data static
    companion object {
        var email = "firdha@gmail.com"
        var namaLengkap = "dicky firmansyah"
        var sandi = "1933"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtEmail:EditText = findViewById(R.id.editEmailR)
        val txtNamaLengkap:EditText = findViewById(R.id.editNamaR)
        val txtSandi:EditText = findViewById(R.id.editSandiR)
        val btnDaftar = findViewById<Button>(R.id.buttonDaftarR)
        btnDaftar.setOnClickListener {
            if(txtEmail.text.toString().equals(email) &&
                txtNamaLengkap.text.toString().equals(namaLengkap) && txtSandi.text.toString().equals(
                    sandi)) {
                val regis = Intent(this, MenuRegister::class.java)
                startActivity(regis)
            }else {
                Toast.makeText(this, "Email atau kata sandi salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}