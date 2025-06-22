# 小米工程师训练营（6.24）
# Day7 任务

## 今日任务：补间动画与属性动画

1. **补间动画**  
   使用Java代码实现：基于当前View中心点放大1.5倍，同时逆时针旋转720度，由不透明变为透明度0.8，持续2000ms，并且重复动画3次。动画开始、重复和结束时分别打印日志。

2. **属性动画**  
   使用Java代码实现：使用AnimatorSet，先让View围绕X轴旋转360度（1000ms），同时向右移动120px（1000ms），最后从不透明变成透明度0.5（500ms）。要求有2个基础动画同时执行，有1个顺序执行，且实现至少2种不同效果的自定义插值器。

## 项目结构

- `app/src/main/java/com/example/day7/`  
  - `MainActivity.java`：主Activity，包含补间动画与属性动画的实现。

- `app/src/main/res/layout/`  
  - `activity_main.xml`：主页面布局，包含ImageView和两个按钮。

- `app/src/main/res/drawable/`  
  - `ic_launcher_foreground.xml`、`ic_launcher_background.xml`：演示图片资源。

- `app/src/main/res/values/`  
  - 主题、颜色、字符串等资源文件。

## 功能说明

- **补间动画**：点击"补间动画"按钮，图片以中心点放大1.5倍，逆时针旋转720度，透明度变为0.8，持续2000ms，重复3次。动画开始、重复和结束时分别打印日志。
- **属性动画**：点击"属性动画"按钮，图片先围绕X轴旋转360度（弹性插值器），同时向右移动120px（缓动插值器），最后透明度从1变为0.5。动画顺序和插值器均为自定义实现。

## 技术要点

- **补间动画**：AnimationSet组合Scale、Rotate、Alpha动画，设置repeatCount和监听器。
- **属性动画**：AnimatorSet组合ObjectAnimator，play/with/after控制顺序，自定义Interpolator。
- **自定义插值器**：实现弹性和缓动两种插值器。
- **日志输出**：动画各阶段输出日志，便于调试和演示。

## 功能演示

页面包含以下主要区域：
1. 图片（ImageView，演示动画）
2. 补间动画按钮
3. 属性动画按钮

如下：

[![9e307f779252ec687461842127acbba.png](https://img.picui.cn/free/2025/06/22/6857ec96d0e30.png)](https://img.picui.cn/free/2025/06/22/6857ec96d0e30.png)
[![c3e36588a20b6884b31d91fe87da3a5.png](https://img.picui.cn/free/2025/06/22/6857ec96c9c1d.png)](https://img.picui.cn/free/2025/06/22/6857ec96c9c1d.png)


----

**Published by**：tttaaayyyx

2025年6月22日

[https://github.com/axxxx1015/XiaomiAndroidLearning](https://github.com/axxxx1015/XiaomiAndroidLearning)

----



 
