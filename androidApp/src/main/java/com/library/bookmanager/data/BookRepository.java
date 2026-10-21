package com.library.bookmanager.data;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public final class BookRepository {

    private static final BookRepository INSTANCE = new BookRepository();

    private final List<Book> books = new ArrayList<>();

    private BookRepository() {
        books.add(new Book("Lập trình Android cơ bản", "Nguyễn Văn A", "Công nghệ", Book.Status.READING, 4, ""));
        books.add(new Book("Đắc nhân tâm", "Dale Carnegie", "Kỹ năng sống", Book.Status.READ, 5, ""));
        books.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài", "Văn học", Book.Status.UNREAD, 0, ""));
    }

    public static BookRepository get() {
        return INSTANCE;
    }

    public List<Book> getAll() {
        return books;
    }

    public void add(Book book) {
        books.add(book);
    }

    public void remove(Book book) {
        books.remove(book);
    }

    public int countByStatus(EnumSet<Book.Status> statuses) {
        int count = 0;
        for (Book book : books) {
            if (statuses.contains(book.getStatus())) {
                count++;
            }
        }
        return count;
    }
}
