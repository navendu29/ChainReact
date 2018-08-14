package com.example.navendu.chainreaction;

import android.content.Context;
import android.widget.Button;


public class MyButton extends Button {

    int count;
    int colour;
    int visit;

    public MyButton(Context context) {
        super(context);
        count = 0;
        colour=0;
        visit=0;
    }
}
