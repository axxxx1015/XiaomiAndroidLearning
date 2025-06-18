# 小米工程师训练营（6.18）
# Day3 任务

## 今日任务：实现帮助与反馈页面

1. **简洁现代UI设计**  
   实现一个帮助与反馈页面，采用简洁、现代、功能导向的设计风格，背景以纯白色为主，整体布局呈纵向排列。

2. **布局优化与性能**  
   使用最少布局层级实现，优化过度绘制，使用include优化布局代码，减少XML代码量。

3. **多控件综合使用**  
   综合使用Toolbar、搜索框、GridLayout、LinearLayout等多种控件，实现合理的布局结构。

## 项目结构

- `app/src/main/java/com/example/day3/`  
  - `HelpFeedbackActivity.java`：主Activity，实现帮助与反馈页面逻辑，包含返回按钮点击事件。

- `app/src/main/res/layout/`  
  - `activity_help_feedback.xml`：主页面布局，包含Toolbar、搜索框、快捷功能区、FAQ区域。
  - `layout_toolbar.xml`：自定义Toolbar布局，包含返回图标和标题。
  - `layout_question_list.xml`：FAQ区域布局，包含常见问题列表。

- `app/src/main/res/drawable/`  
  - `bg_search_box.xml`：搜索框圆角背景shape。

- `app/src/main/res/values/`  
  - 定义各类资源，使用NoActionBar主题避免系统ActionBar显示。

## 功能说明

- **自定义Toolbar**：顶部白色Toolbar，包含返回按钮和"帮助与反馈"标题。
- **搜索功能**：圆角搜索框，支持关键词输入搜索。
- **快捷功能区**：4列2行GridLayout布局，包含6个功能入口（重设密码、意见反馈、订单查询、常见问题、其他问题、人工客服）。
- **FAQ区域**：常见问题列表，包含多条问题项。
- **布局优化**：使用include减少代码重复，最小化布局层级，优化过度绘制。

## 技术要点

- **布局层级优化**：主布局仅3层，使用LinearLayout + GridLayout + 控件的最简结构。
- **include复用**：Toolbar和FAQ区域使用include引入，减少XML代码量。
- **主题设置**：使用Theme.AppCompat.Light.NoActionBar避免系统ActionBar干扰。
- **响应式设计**：GridLayout和FAQ区域使用layout_weight自适应填满屏幕。

## 功能演示

页面包含以下主要区域：
1. 顶部白色Toolbar（返回按钮 + 标题）
2. 搜索框（圆角、带搜索图标）
3. 快捷功能区（6个功能图标，4列2行布局）
4. 常见问题区域（标题 + 问题列表）

如下：

<img src="https://img.picui.cn/free/2025/06/18/6852c4ad345fe.jpg" alt="71204d1d6779a9dd443cea747fa39ca.jpg" style="zoom:25%;" />



----

**Published by**：tttaaayyyx

2025年6月18日

[https://github.com/axxxx1015/XiaomiAndroidLearning](https://github.com/axxxx1015/XiaomiAndroidLearning)

----



 
