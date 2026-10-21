package com.library.bookmanager.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ShelfRow extends LinearLayout {

    public ShelfRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        setGravity(android.view.Gravity.BOTTOM);
    }
}
