package com.example.day4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.example.day4.ICalcAidlInterface;

public class CalcService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private final ICalcAidlInterface.Stub binder = new ICalcAidlInterface.Stub() {
        @Override
        public int calculate(int a, int b, String op) throws RemoteException {
            switch (op) {
                case "+": return a + b;
                case "-": return a - b;
                case "*": return a * b;
                case "/": return b != 0 ? a / b : 0;
                default: return 0;
            }
        }
    };
} 