package com.library.bookmanager.data;

import java.util.List;

public final class Books {

    private Books() {
    }

    public static String stats(List<Book> books) {
        int total = books.size();
        int read = 0;
        int unread = 0;
        for (Book book : books) {
            if (book.getStatus() == Book.Status.READ) {
                read++;
            } else {
                unread++;
            }
        }
        return "Tổng: " + total + " sách  ·  Đã đọc: " + read + "  ·  Chưa đọc xong: " + unread;
    }
}
