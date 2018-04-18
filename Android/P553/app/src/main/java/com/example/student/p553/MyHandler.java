package com.example.student.p553;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MyHandler extends Handler {
    int resArr[] = {R.drawable.icecream, R.drawable.icecream2};
    int resArr2[] = {R.drawable.icon1, R.drawable.icon2};
    ImageView imageView;

    public MyHandler() {
        super();
    }

    public MyHandler(ImageView imageView) {
        super();
        this.imageView = imageView;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        Bundle bundle = msg.getData();

        int res = bundle.getInt("data");
        imageView.setImageResource(resArr[res%2]);
    }
}
