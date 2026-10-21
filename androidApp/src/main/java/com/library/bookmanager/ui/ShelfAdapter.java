package com.library.bookmanager.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.library.bookmanager.R;
import com.library.bookmanager.data.Book;

import java.util.List;

public class ShelfAdapter extends BaseAdapter {

    public interface Listener {
        void onOpen(Book book);
    }

    private static final int PER_SHELF = 3;
    private static final int SHELVES = 5;

    private final Context context;
    private final LayoutInflater inflater;
    private final List<Book> books;
    private final Listener listener;

    public ShelfAdapter(Context context, List<Book> books, Listener listener) {
        this.context = context.getApplicationContext();
        this.inflater = LayoutInflater.from(context);
        this.books = books;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return SHELVES;
    }

    @Override
    public Integer getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView != null ? convertView : inflater.inflate(R.layout.item_shelf, parent, false);
        ShelfRow row = view.findViewById(R.id.shelfRow);
        row.removeAllViews();
        for (int i = 0; i < PER_SHELF; i++) {
            int index = position * PER_SHELF + i;
            if (index >= books.size()) {
                row.addView(blank(row));
                continue;
            }
            row.addView(face(books.get(index), row));
        }
        return view;
    }

    private View blank(ViewGroup row) {
        return inflater.inflate(R.layout.item_shelf_blank, row, false);
    }

    private View face(Book book, ViewGroup row) {
        View face = inflater.inflate(R.layout.item_book_face, row, false);
        float radius = context.getResources().getDimension(R.dimen.cover_radius);
        face.findViewById(R.id.cover).setBackground(BookSpines.cover(book, radius));
        ((TextView) face.findViewById(R.id.tvTitle)).setText(book.getTitle());
        ((TextView) face.findViewById(R.id.tvAuthor)).setText(book.getAuthor());
        ((TextView) face.findViewById(R.id.tvStatus)).setText(book.getStatus().label);
        ((TextView) face.findViewById(R.id.tvRating)).setText(
                context.getString(R.string.rating_badge, Math.round(book.getRating())));
        face.setOnClickListener(v -> listener.onOpen(book));
        return face;
    }
}
