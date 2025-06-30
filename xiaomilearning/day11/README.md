# 小米工程师训练营（6.26）
# Day11 任务

## 今日主任务：多类型滑动列表 + 点赞 + 详情页

1. **首页滑动列表**
   - RecyclerView 实现，支持文本和图片两种类型（交错出现）
   - 图片用 Glide 加载网络图（https://picsum.photos/400/xxx，随机高度）
   - 每项都能点赞，状态可切换
   - 下拉刷新，重新生成内容

2. **详情页**
   - 点击文本项进入文本详情，点击图片项进入大图+文案详情
   - 详情页也能点赞/取消，操作后状态同步回首页

## 项目结构

- `app/src/main/java/com/example/day11/`
  - `MainActivity.java`：主界面，列表、刷新、点赞、跳转详情
  - `MyAdapter.java`：多类型适配器，处理点赞和点击
  - `ListItem.java`：数据结构，区分文本/图片
  - `DetailActivity.java`：详情页，点赞同步
- `app/src/main/res/layout/`：主界面、详情页、列表项布局
- 其他资源：点赞图标、主题、颜色等

## 技术点 & 细节
- RecyclerView 多类型写法，lambda 回调
- Glide 网络图片加载，带占位/错误图
- SwipeRefreshLayout 下拉刷新
- Activity 结果回传同步点赞
- 图片链接用 picsum.photos，真机网络更稳定

## 运行体验
1. 用 Android Studio 打开 day11
2. 直接运行 app 模块，建议用真机（模拟器有时网络不通）
3. 下拉刷新、点赞、跳转详情都能体验

## 个人小结
- 这次主要练习了多类型列表和数据同步，细节比前几天多不少
- 图片加载如果遇到问题，优先排查网络和权限
- 代码结构尽量写得清楚，方便后续迁移到 Kotlin

----

**Published by**：tttaaayyyx

2025年6月26日

[https://github.com/axxxx1015/XiaomiAndroidLearning](https://github.com/axxxx1015/XiaomiAndroidLearning)

----

# Day11 作业说明

## 功能简介
- 使用 Java 实现滑动列表，支持文本和图片两种类型
- 列表项可点赞，支持下拉刷新
- 图片类型点击可查看大图和文案，文本类型点击可查看详情
- 详情页支持点赞/取消点赞，状态可同步回首页
- 图片使用网络图片，Glide 加载 https://picsum.photos/400/xxx

## 技术要点
- RecyclerView 多类型适配器
- Glide 网络图片加载
- 下拉刷新：SwipeRefreshLayout
- Activity 之间数据同步

## 运行说明
1. Android Studio 打开 day11 工程
2. 连接真机或模拟器（需保证网络可用）
3. 运行 app 模块即可体验

## 备注
- 如图片无法加载，请检查网络权限和设备网络环境
- 代码结构清晰，便于后续迁移到 Kotlin 或扩展功能

 
