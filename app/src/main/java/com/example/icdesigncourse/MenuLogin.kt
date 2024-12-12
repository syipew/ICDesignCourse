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
import com.example.icdesigncourse.client.RetrofitClient
import com.example.icdesigncourse.response.akun.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MenuLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtUsername = findViewById<EditText>(R.id.isiEmail)
        val txtPassword = findViewById<EditText>(R.id.isiPassword)
        val btnLogin = findViewById<Button>(R.id.btnMasuk)
        btnLogin.setOnClickListener {
            val username = txtUsername.text.toString().trim()
            val pwd = txtPassword.text.toString().trim()
            if(username.isEmpty()) {
                txtUsername.error = "Username harus diisi"
                txtUsername.requestFocus()
                return@setOnClickListener
            }
            if(pwd.isEmpty()) {
                txtPassword.error = "Password harus diisi"
                txtPassword.requestFocus()
                return@setOnClickListener
            }
            RetrofitClient.instance.login(username, pwd).enqueue(
                object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val akun = response.body()
                        if(akun?.success == true) {
                            Toast.makeText(this@MenuLogin, akun.message.toString(), Toast.LENGTH_SHORT).show()
                            val intentLogin = Intent(this@MenuLogin, RoleProfile_Activity::class.java)
                            startActivity(intentLogin)
                        }else {
                            Toast.makeText(this@MenuLogin, akun?.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message,Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}