# 项目说明文档（字节跳动工程训练营第二次作业）

本项目是一个基于 Kotlin 开发的 Android 应用，主要展示用户个人信息页面，包含账号信息、个性签名和功能菜单列表。

---

## 📱 功能特性

### 1. 用户信息展示
- 显示从登录页面通过 `Intent` 传递的 **邮箱账号** 和 **密码**
- 账号名显示在页面顶部的 `accountName` TextView 中

### 2. 个性签名管理
- 使用 `SharedPreferences` 持久化存储用户的个性签名
- 默认签名：**"她掉一滴泪,我屠一座城~"**
- 支持后续扩展为可编辑的个性签名功能

### 3. 功能菜单列表
- 通过 `RecyclerView` 实现垂直滚动的菜单列表
- 每个菜单项包含图标和标题
- 当前包含以下功能入口:
  - 🧑 个人信息
  - ℹ️ 关于我们
  - 💬 意见反馈
  - ⭐ 我的收藏
  - 📜 浏览历史
  - 👥 社区

---

## 🏗️ 技术架构

### 开发语言与工具
- **开发语言**: Kotlin
- **构建工具**: Gradle
- **IDE**: Android Studio Otter | 2025.2.1

### 核心组件
| 组件 | 用途 |
|------|------|
| `AppCompatActivity` | 基础活动类,启用边到边显示 |
| `RecyclerView` | 高效渲染可滚动菜单列表 |
| `LinearLayoutManager` | 垂直线性布局管理器 |
| `SharedPreferences` | 轻量级键值对存储 |
| `Intent` | 跨页面数据传递 |

---

## 📂 项目结构

```
app/src/main/java/com/example/secondhomework_pages/
├── UserPage.kt          # 用户页面主活动
├── MenuAdapter.kt       # RecyclerView 适配器
├── MenuItem.kt          # 菜单项数据类
└── R.layout             # 布局资源文件
    └── activity_user_page.xml
```

---

## 🔍 代码详解

### 核心流程

#### 1. 页面初始化
```kotlin
enableEdgeToEdge()  // 启用全屏沉浸式显示
setContentView(R.layout.activity_user_page)  // 绑定布局
```

#### 2. 获取登录信息
```kotlin
val emailAccount = intent.getStringExtra("emailAccount")  // 从 Intent 获取邮箱
val password = intent.getStringExtra("password")          // 从 Intent 获取密码
```

#### 3. SharedPreferences 数据持久化
```kotlin
// 写入数据(存储个性签名)
val editor = getSharedPreferences("personalSign", Context.MODE_PRIVATE).edit()
editor.putString("sign", "她掉一滴泪,我屠一座城~")
editor.apply()

// 读取数据(显示个性签名,未找到则使用默认值)
val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
personSign.text = prefs.getString("sign", "欢迎来到信息App~")
```

> ⚠️ **注意**: 代码中存在逻辑不一致 —— 写入使用 `"personalSign"`,读取使用 `"data"`,建议统一为同一个文件名

#### 4. RecyclerView 配置
```kotlin
// 设置布局管理器(垂直滚动)
recyclerView.layoutManager = LinearLayoutManager(this)

// 创建菜单数据列表
val menuItems = listOf(
    MenuItem(R.drawable.person, "个人信息"),
    // ... 更多菜单项
)

// 绑定适配器
recyclerView.adapter = MenuAdapter(menuItems)
```

---

## 💾 数据存储方案

### SharedPreferences 配置
| 键名 | 存储内容 | 模式 |
|------|----------|------|
| `"personalSign"` | 用户个性签名 | `MODE_PRIVATE` |

**存储位置**: `/data/data/com.example.secondhomework_pages/shared_prefs/`

