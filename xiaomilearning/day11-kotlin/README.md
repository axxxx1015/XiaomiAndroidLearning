# 小米工程师训练营（6.26）
# Day11-Kotlin 任务

## 今日主任务：Kotlin 版多类型滑动列表 + 点赞 + 详情页

1. **首页滑动列表**
   - 全部用 Kotlin 写的 RecyclerView，多类型（文本+图片）
   - 图片用 Glide 加载网络图（https://picsum.photos/400/xxx，随机高度）
   - 每项都能点赞，状态可切换
   - 下拉刷新，内容随机生成

2. **详情页**
   - 点击文本项进文本详情，图片项进大图详情
   - 详情页点赞/取消，状态同步回首页

## 项目结构

- `app/src/main/java/com/example/day11/`
  - `MainActivity.kt`：主界面，Kotlin 写法，列表、刷新、点赞、跳转详情
  - `MyAdapter.kt`：Kotlin 多类型适配器，lambda 回调
  - `ListItem.kt`：Kotlin 数据类，区分文本/图片
  - `DetailActivity.kt`：详情页，点赞同步
- `app/src/main/res/layout/`：主界面、详情页、列表项布局（和 Java 版复用）
- 其他资源：点赞图标、主题、颜色等

## 技术点 & 细节
- 全部 Kotlin idiomatic 写法，代码更简洁
- RecyclerView 多类型、lambda 回调
- Glide 网络图片加载，带占位/错误图
- SwipeRefreshLayout 下拉刷新
- Activity 结果回传同步点赞
- 图片链接用 picsum.photos，真机网络更稳定

## 运行体验
1. 用 Android Studio 打开 day11-kotlin
2. 直接运行 app 模块，建议用真机（模拟器有时网络不通）
3. 下拉刷新、点赞、跳转详情都能体验

<img src="https://img.picui.cn/free/2025/06/26/685d5ddd4ee7e.jpg" alt="47e6359c9f79739b5b1815891525877.jpg" style="zoom:25%;" />

## 个人小结
- 这版是 day11 的 Kotlin 重写，风格更现代，java语言版在同级链接
- 适合对比 Java/Kotlin 写法差异
- 图片加载如果遇到问题，优先排查网络和权限

----

**Published by**：tttaaayyyx

2025年6月26日

[https://github.com/axxxx1015/XiaomiAndroidLearning](https://github.com/axxxx1015/XiaomiAndroidLearning)

----



 
