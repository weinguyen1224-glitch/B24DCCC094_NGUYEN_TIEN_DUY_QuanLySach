package com.library.bookmanager.ui;

import android.graphics.drawable.GradientDrawable;

import com.library.bookmanager.data.Book;

public final class BookSpines {

    private static final int[][] PALETTE = {
            {0xFF7B4B2A, 0xFF5C3620},
            {0xFF2F5D62, 0xFF1F4044},
            {0xFF8C2F39, 0xFF63202A},
            {0xFF3E5C2F, 0xFF2A3F20},
            {0xFF4B3F72, 0xFF332A50},
            {0xFFB08968, 0xFF8A6448},
    };

    private BookSpines() {
    }

    public static GradientDrawable spine(Book book, float cornerRadius) {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, palette(book));
        drawable.setCornerRadius(cornerRadius);
        drawable.setStroke(1, 0x40201008);
        return drawable;
    }

    public static GradientDrawable cover(Book book, float radius) {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, palette(book));
        drawable.setCornerRadius(radius);
        drawable.setStroke(1, 0x66201008);
        return drawable;
    }

    private static int[] palette(Book book) {
        return PALETTE[Math.floorMod(book.hashCode(), PALETTE.length)];
    }
}
