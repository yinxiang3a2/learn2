package com.example.secondhomework_pages

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.CheckBox
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_page)

        val dbHelper = MyDatabaseHelper(this,"Account.db",1)
        val create: Button = findViewById(R.id.create)
        val eaccount: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)

        // --- 注册按钮逻辑 ---
        create.setOnClickListener {
            val ea = eaccount.text.toString()
            val pa = password.text.toString()
            val db = dbHelper.writableDatabase
            val value = ContentValues().apply {
                put("emailAccount", ea)
                put("password", pa)
            }
            db.insert("Account", null, value)

            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
