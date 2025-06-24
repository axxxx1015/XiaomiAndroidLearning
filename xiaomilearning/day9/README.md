# 小米工程师训练营（6.24）
# Day9 任务

## 今日主题：迪米特法则与多设计模式融合实践

### 任务要求

1. **理解迪米特法则**
   - 每个类只与”直接朋友“通信，降低耦合，提升系统可维护性。

2. **实现带双重锁的懒汉式单例**
   - 线程安全，延迟初始化，保证全局唯一实例。

3. **设计"咖啡店订单系统"，融合多种设计模式并实现完整交互界面**
   - **工厂模式**：`CoffeeFactory` 根据类型创建不同咖啡对象，便于扩展。
   - **装饰者模式**：`CoffeeDecorator` 及其子类动态为咖啡添加配料，避免类爆炸。
   - **观察者模式**：`OrderObserver` 实现订单状态通知，顾客和厨房自动接收更新。
   - **单例模式**：`OrderManager` 作为全局唯一订单管理器，统一管理订单和通知。
   - **策略模式**：`PaymentStrategy` 封装不同支付方式，运行时灵活切换。

### 项目结构

- `app/src/main/java/com/example/day9/`
  - `Singleton.java`：双重锁懒汉式单例
  - `OrderManager.java`：订单管理器（单例+观察者）
  - `OrderObserver.java`：订单状态观察者接口
  - `Order.java`：订单实体
  - `Coffee.java`、`Espresso.java`、`Latte.java`：咖啡基类及实现
  - `CoffeeFactory.java`：工厂模式
  - `CoffeeDecorator.java`、`MilkDecorator.java`、`SugarDecorator.java`：装饰者模式
  - `PaymentStrategy.java`、`AlipayStrategy.java`、`WechatPayStrategy.java`：策略模式
  - `MainActivity.java`：主流程与交互界面
- `app/src/main/res/layout/activity_main.xml`：主界面布局

### 主要功能说明

- 支持多种咖啡类型和配料灵活组合
- 订单状态变更自动通知顾客和厨房
- 支持支付宝、微信等多种支付方式
- 交互式界面，用户可自定义下单流程
- 代码结构清晰，易于扩展和维护

### 交互界面说明

- 选择咖啡类型（Espresso/Latte）
- 勾选配料（牛奶、糖）
- 选择支付方式（支付宝/微信）
- 点击"下单"按钮后，界面动态展示下单、支付、订单状态变化和顾客/厨房通知

界面示例：

[![ec9c7627c4f75327cf3cbd33b700404.png](https://img.picui.cn/free/2025/06/24/685a9f4198a42.png)](https://img.picui.cn/free/2025/06/24/685a9f4198a42.png)

[![8a30f9854cfcfd51f6dfacb5f5ae2f1.png](https://img.picui.cn/free/2025/06/24/685a9f4190453.png)](https://img.picui.cn/free/2025/06/24/685a9f4190453.png)

### 设计模式应用示例

```java
// 创建一杯加牛奶和糖的拿铁
Coffee coffee = CoffeeFactory.createCoffee("Latte");
coffee = new MilkDecorator(coffee);
coffee = new SugarDecorator(coffee);

// 支付
PaymentStrategy payment = new AlipayStrategy();
payment.pay(coffee.cost());

// 订单状态通知
Order order = new Order(coffee.getDescription());
OrderManager.getInstance().addObserver(new CustomerObserver());
OrderManager.getInstance().addObserver(new KitchenObserver());
order.setStatus(Order.Status.PAID);
OrderManager.getInstance().notifyOrderStatusChanged(order);
```

----

**Published by**：tttaaayyyx

2025年6月24日

[https://github.com/axxxx1015/XiaomiAndroidLearning](https://github.com/axxxx1015/XiaomiAndroidLearning)

----



 
