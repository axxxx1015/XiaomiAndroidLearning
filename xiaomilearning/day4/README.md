# 小米工程师训练营（6.19）
# Day4 任务：计算器

## 作业要求

1. **实现一个简单的计算器**
   - 支持个位数的加、减、乘、除四则运算
   - 界面上有按钮 0-9、+、-、*、/、=
2. **计算过程在 Service 中进行**
   - Activity 端只负责输入和显示
   - 实际运算通过 AIDL 跨进程调用 Service 完成
3. **使用 AIDL 进行跨进程通信**
   - 通过 AIDL 文件定义计算接口
   - Activity 通过 bindService 绑定 Service，调用远程方法
4. **加分项**
   - 使用 RecyclerView 实现计算器的按键区，布局美观，易于扩展

## 功能说明

- 支持输入第一个数字，选择运算符，输入第二个数字，点击"="显示结果
- 支持"C"键清空输入
- 除数为0时有提示
- 按键全部通过 RecyclerView 实现
- 结果和输入实时显示在顶部

## 运行效果

- 启动应用即进入计算器界面

- 点击按键输入数字和运算符，点击"="即可看到结果

- 点击"C"可清空输入

- 除以0时显示"除数不能为0"

  演示如下

  <img src="https://img.picui.cn/free/2025/06/19/6854057521b0e.png" alt="cf0f1766e4e26a957e80cbe305265a5.png" style="zoom: 67%;" />

  <img src="https://img.picui.cn/free/2025/06/19/685405f1ca10a.png" alt="5f7d20c78a1ee24e47a54b577c2858c.png" style="zoom: 50%;" />

  <img src="https://img.picui.cn/free/2025/06/19/68540638a0f04.png" alt="9051b2393c4ce3d0c28cbbcda5f9118.png" style="zoom:50%;" />

## 目录结构

```
day4/
  app/
    src/
      main/
        aidl/
          com/example/day4/ICalcAidlInterface.aidl
        java/
          com/example/day4/AidlActivity.java
          com/example/day4/CalcService.java
          com/example/day4/KeyAdapter.java
        res/
          layout/activity_aidl.xml
          layout/item_key.xml
          drawable/bg_key.xml
          values/strings.xml
          values/styles.xml
          values/themes.xml
    build.gradle.kts
    ...
```

## 主要代码说明

- `ICalcAidlInterface.aidl`：AIDL接口，定义 `int calculate(int a, int b, String op);`
- `CalcService.java`：Service端，负责实际加减乘除运算
- `AidlActivity.java`：主界面，负责输入、显示、AIDL调用
- `KeyAdapter.java`：RecyclerView适配器，管理按键点击
- `activity_aidl.xml`：主界面布局
- `item_key.xml`、`bg_key.xml`：按键样式

## 拓展方向

进一步完善：

多位数字的计算--已完成

清空输入区按键--已完成

运算提示--已经完成除数为0的提示，符号连用、误用等非法情况正在开发中

小数、负数的计算，高级运算符，连续运算

输入记录以及历史记录

......

----

**Published by**：tttaaayyyx

2025年6月19日

[https://github.com/axxxx1015/XiaomiAndroidLearning](https://github.com/axxxx1015/XiaomiAndroidLearning)

----



 

 
