# 小米工程师训练营（6.23）
# Day8 任务

## 今日任务：自定义标签云控件

### 任务要求

1. **实现自定义 View：标签云控件**
   - 支持自定义属性：标签的左右边距、上下边距
   - 支持标签自动换行排列
   - 每个标签以矩形框形式展示
   - 支持手指拖动滑动整个标签云

2. **内容示例**
   - 标签内容如"标签1"、"标签2"、"标签3"等

### 项目结构

- `app/src/main/java/com/example/day8/`
  - `MainActivity.java`：主界面，演示标签云控件
  - `TagCloudView.java`：自定义标签云 View，支持自定义属性和滑动
- `app/src/main/res/layout/`
  - `activity_main.xml`：主界面布局，集成 TagCloudView
- `app/src/main/res/values/`
  - `attrs.xml`：自定义属性声明
  - 主题、颜色、字符串等资源文件

### 功能说明

- **自定义属性**：可通过 XML 设置标签的左右/上下边距
- **自动换行**：标签自动按行排列，超出宽度自动换行
- **滑动支持**：手指拖动可滑动整个标签云区域
- **标签样式**：每个标签以带边框的矩形展示，内容可自定义

### 技术要点

- 继承 View 实现自定义控件
- 使用 TypedArray 读取自定义属性
- onDraw 绘制标签及边框，动态计算换行
- onTouchEvent 实现滑动逻辑
- 支持 setTags 动态设置标签内容

### 功能演示

页面包含如下主要区域：
1. 标签云控件（支持滑动、换行、边距自定义）
2. 多个示例标签
[![05349a8a-28b5-42ab-8768-e6b97611b847.png](https://img.picui.cn/free/2025/06/23/6859585b28e79.png)](https://img.picui.cn/free/2025/06/23/6859585b28e79.png)
[![602fc5de-d4b5-49fc-bbe2-fc6d1b7cbe7c.png](https://img.picui.cn/free/2025/06/23/6859585b19b88.png)](https://img.picui.cn/free/2025/06/23/6859585b19b88.png)

----

**Published by**：tttaaayyyx

2025年6月23日

[https://github.com/axxxx1015/XiaomiAndroidLearning](https://github.com/axxxx1015/XiaomiAndroidLearning)

----



 
