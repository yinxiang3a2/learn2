package com.example.secondhomework_pages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class UsPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_page)

        // 1. 获取传递过来的账号
        val emailAccount = intent.getStringExtra("emailAccount") ?: "未登录"

        // 2. 绑定控件
        val accountName: TextView = findViewById(R.id.accountName)
        val personSign: TextView = findViewById(R.id.personSign)

        // 3. 读取本地签名
        val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
        val signText = prefs.getString("sign", "欢迎")
        personSign.text = signText
        accountName.text = emailAccount

        // 4. 菜单数据（去掉重复）
        val menuItems = listOf(
            MenuItem(R.drawable.person, "个人信息"),
            MenuItem(R.drawable.aboutus, "关于我们"),
            MenuItem(R.drawable.advice, "意见反馈"),
            MenuItem(R.drawable.preferences, "我的收藏"),
            MenuItem(R.drawable.history, "浏览历史"),
            MenuItem(R.drawable.community, "社区"),
        )

        // 5. 配置 RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview_id)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UsPage)
            adapter = MenuAdapter(menuItems)
        }
    }
}
