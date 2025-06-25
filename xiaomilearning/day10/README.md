# 小米工程师训练营（6.25）
# Day10 任务

## 今日任务：ANR 检测与堆栈展示

### 任务要求

1. **实现 ANR 检测功能**
   - 程序启动后自动开启 ANR 检测线程（原理类似 ANRWatchDog）
   - 检测到 ANR 时，能够获取并展示主线程堆栈信息

2. **界面要求**
   - 顶部有一个大按钮，点击后可触发 ANR（主线程 sleep）
   - 下方有一个多行文本框，实时显示检测到的 ANR 堆栈

### 项目结构

- `app/src/main/java/com/example/day10/`
  - `MainActivity.java`：主界面，负责 ANR 检测、触发与堆栈展示
- `app/src/main/res/layout/`
  - `activity_anr.xml`：主界面布局，包含按钮和堆栈文本框
- 其他资源文件：主题、颜色、字符串等

### 功能说明

- **ANR 检测**：通过子线程定时检测主线程响应，模拟 ANRWatchDog 原理
- **堆栈展示**：检测到 ANR 时，自动获取主线程堆栈并展示在文本框
- **一键触发**：点击按钮，主线程 sleep，方便演示 ANR 效果

### 技术要点

- Handler+Looper 检测主线程卡死
- 获取主线程堆栈 StackTrace
- UI 实时更新
- 简单易懂，便于实验和演示

### 功能演示

页面包含如下主要区域：
1. 顶部大按钮（触发 ANR）
2. 下方多行文本框（显示 ANR 堆栈）

[![71f3ad68223f2f907f351b329875fba.png](https://img.picui.cn/free/2025/06/25/685bf6f60db3f.png)](https://img.picui.cn/free/2025/06/25/685bf6f60db3f.png)
[![29d5991f92d52694f3603045295581a.png](https://img.picui.cn/free/2025/06/25/685bf6f6896b2.png)](https://img.picui.cn/free/2025/06/25/685bf6f6896b2.png)

----

**Published by**：tttaaayyyx

2025年6月25日

[https://github.com/axxxx1015/XiaomiAndroidLearning](https://github.com/axxxx1015/XiaomiAndroidLearning)

----



 
