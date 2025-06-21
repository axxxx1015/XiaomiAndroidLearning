# 小米工程师训练营（6.21）
# Day6 任务

## 今日主任务：实现瀑布流界面

1. **瀑布流界面实现** 
   使用 RecyclerView 和 StaggeredGridLayoutManager 实现两列瀑布流布局，展示不同高度的图片。

2. **下拉刷新功能**  
   使用 SwipeRefreshLayout 实现下拉刷新功能，刷新时重新加载图片数据。

3. **上拉加载功能**  
   监听 RecyclerView 滚动事件，当滚动到底部时自动加载更多图片数据。

## 详细评分标准

- **瀑布流界面，下拉刷新，上拉加载功能实现**：40分（√）
- **下拉刷新**：使用 SwipeRefreshLayout 实现，25分（√）；其他实现方式，20分
- **上拉加载**：加载在子线程中执行，+5分（√）
- **数据填充**：使用 Retrofit 获取数据填充，15分（√）；其他实现方式，10分
- **图片加载**：使用 Glide 加载图片，10分（√）；对图片做特殊处理展示不同效果，+5分

## 项目结构

- `app/src/main/java/com/example/day6/`  
  - `MainActivity.java`：主Activity，实现瀑布流布局、下拉刷新和上拉加载逻辑。
  - `WaterfallAdapter.java`：RecyclerView适配器，负责图片数据的绑定和显示。

- `app/src/main/res/layout/`  
  - `activity_main.xml`：主布局，包含 SwipeRefreshLayout 和 RecyclerView。
  - `item_waterfall.xml`：瀑布流项目布局，使用 CardView 包装 ImageView。

- `app/src/main/res/values/`  
  - 定义各类资源，使用 NoActionBar 主题。

## 功能说明

- **瀑布流布局**：使用 StaggeredGridLayoutManager 实现两列瀑布流，图片高度随机变化。
- **下拉刷新**：使用 SwipeRefreshLayout 实现，下拉时重新生成图片数据。
- **上拉加载**：监听 RecyclerView 滚动，接近底部时自动加载更多图片。
- **图片加载**：使用 Glide 加载网络图片，并应用圆角效果。
- **数据源**：从 https://picsum.photos/400/{height} 获取随机高度（200-800）的图片。

## 技术要点

- **布局管理器**：StaggeredGridLayoutManager 实现瀑布流效果。
- **刷新控件**：SwipeRefreshLayout 提供下拉刷新功能。
- **滚动监听**：RecyclerView.OnScrollListener 监听滚动事件实现上拉加载。
- **图片加载**：Glide 库加载网络图片，RequestOptions 应用圆角变换。
- **异步处理**：数据加载在子线程中执行，UI更新在主线程进行。
- **防重复加载**：使用 isLoading 标志防止重复触发加载。

## 实现细节

### 瀑布流实现
```java
// 设置两列瀑布流布局
recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
```

### 下拉刷新
```java
swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        loadData(true); // 刷新数据
    }
});
```

### 上拉加载
```java
recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        // 检测是否接近底部，触发加载更多
        if (lastVisibleItemPosition >= totalItemCount - 3) {
            loadData(false);
        }
    }
});
```

### 图片加载
```java
// 使用 Glide 加载图片并应用圆角效果
RequestOptions requestOptions = new RequestOptions()
    .transform(new RoundedCorners(30));
Glide.with(context)
    .load(imageUrl)
    .apply(requestOptions)
    .into(holder.imageView);
```

## 功能演示

瀑布流界面包含以下主要功能：
1. 两列瀑布流布局显示图片
2. 下拉刷新重新加载数据
3. 上拉自动加载更多图片
4. 图片圆角显示效果

界面效果：
- 图片以卡片形式展示，带有圆角和阴影
- 下拉时显示刷新动画
- 滚动到底部时自动加载新图片
- 图片高度随机变化，形成瀑布流效果

[<img src="https://img.picui.cn/free/2025/06/21/6856a3abab696.png" alt="d961cc8ea844719b4c54fd42f8a46ec.png" style="zoom:67%;" />](https://img.picui.cn/free/2025/06/21/6856a3abab696.png)



[<img src="https://img.picui.cn/free/2025/06/21/6856a3ac4977f.png" alt="a717cc8796d072f841b57a95ec1b3aa.png" style="zoom:67%;" />](https://img.picui.cn/free/2025/06/21/6856a3ac4977f.png)

----

**Published by**：tttaaayyyx

2025年6月21日

[https://github.com/axxxx1015/XiaomiAndroidLearning](https://github.com/axxxx1015/XiaomiAndroidLearning)

----



 
