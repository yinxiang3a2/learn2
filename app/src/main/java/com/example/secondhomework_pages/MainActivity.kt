package com.example.secondhomework_pages

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
class MainActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var dbHelper: MyDatabaseHelper
    private var isPasswordVisible = false  // <-- 类成员变量

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 初始化数据库（避免每次点击都 new）
        dbHelper = MyDatabaseHelper(this, "Account.db", 1)

        initViews()
        initListeners()
    }

    private fun initViews() {
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
    }

    private fun initListeners() {
        findViewById<TextView>(R.id.forgetPassword).setOnClickListener {
            showToast("找回密码")
        }

        findViewById<Button>(R.id.loginBtn).setOnClickListener {
            handleLogin()
        }

        findViewById<TextView>(R.id.register).setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        findViewById<Button>(R.id.wechatLoginBtn).setOnClickListener {
            showToast("Wei xin login")
        }

        findViewById<Button>(R.id.appleLoginBtn).setOnClickListener {
            showToast("Apple login")
        }
        passwordInput.setOnTouchListener { v, event ->
            if (event.action == android.view.MotionEvent.ACTION_UP) {
                val drawableEnd = 2 // 右侧 drawable
                val drawable = passwordInput.compoundDrawables[drawableEnd]
                if (drawable != null) {
                    if (event.rawX >= (passwordInput.right - drawable.intrinsicWidth - passwordInput.paddingEnd)) {
                        isPasswordVisible = !isPasswordVisible
                        if (isPasswordVisible) {
                            passwordInput.transformationMethod = null // 明文显示
                        } else {
                            passwordInput.transformationMethod =
                                android.text.method.PasswordTransformationMethod.getInstance() // 隐藏
                        }
                        passwordInput.setSelection(passwordInput.text.length)
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }

    }

    private fun handleLogin() {
        val email = emailInput.text.toString().trim()
        val pass = passwordInput.text.toString().trim()

        if (email.isEmpty() || pass.isEmpty()) {
            showToast("请输入账号和密码")
            return
        }


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("邮箱格式不正确")
            return
        }

        val db = dbHelper.readableDatabase

        db.query(
            "Account",
            null,
            "emailAccount = ? AND password = ?",
            arrayOf(email, pass),
            null,
            null,
            null
        ).use { cursor ->
            if (cursor.moveToFirst()) {
                // 登录成功
                showToast("登录成功")
                navigateToUserPage(email, pass)
            } else {
                showToast("账号或密码错误")
                emailInput.text.clear()
                passwordInput.text.clear()
            }
        }

        db.close()
    }

    private fun navigateToUserPage(email: String, password: String) {
        val intent = Intent(this, UsPage::class.java)
        intent.putExtra("emailAccount", email)
        intent.putExtra("password", password)
        startActivity(intent)
        finish() // 返回键不跳回登录页
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}
