package com.example.day4;

// 计算器AIDL接口，支持加减乘除
interface ICalcAidlInterface {
    int calculate(int a, int b, String op);
}