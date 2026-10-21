package com.library.bookmanager.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.library.bookmanager.R;
import com.library.bookmanager.data.Book;
import com.library.bookmanager.data.BookRepository;

public class BookDetailActivity extends Activity {

    private static final String EXTRA_BOOK = "book";

    public static Intent intentOf(Context context, Book book) {
        return new Intent(context, BookDetailActivity.class).putExtra(EXTRA_BOOK, book);
    }

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        book = (Book) getIntent().getSerializableExtra(EXTRA_BOOK);
        if (book == null) {
            finish();
            return;
        }

        bindBook();

        ((TextView) findViewById(R.id.tvToolbarTitle)).setText(R.string.detail_title);
        findViewById(R.id.btnBack).setOnClickListener(view -> finish());
        findViewById(R.id.btnMarkRead).setOnClickListener(view -> markRead());
        findViewById(R.id.btnDelete).setOnClickListener(view -> confirmDelete());
    }

    private void bindBook() {
        TextView title = findViewById(R.id.tvTitle);
        title.setText(book.getTitle());
        ((TextView) findViewById(R.id.tvCoverAuthor)).setText(book.getAuthor());
        findViewById(R.id.cover).setBackground(BookSpines.cover(book, getResources().getDimension(R.dimen.cover_radius)));

        ((TextView) findViewById(R.id.tvAuthorGenre)).setText(getString(R.string.detail_author_genre, book.getAuthor(), book.getGenre()));
        ((TextView) findViewById(R.id.tvStatus)).setText(book.getStatus().label);

        String note = book.getNote();
        ((TextView) findViewById(R.id.tvNote)).setText(note.isEmpty() ? getString(R.string.detail_empty_note) : note);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(book.getRating());
        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            book.setRating(rating);
            Toast.makeText(this, R.string.detail_rating_changed, Toast.LENGTH_SHORT).show();
        });

        applyStatus();
    }

    private void markRead() {
        book.setStatus(Book.Status.READ);
        ((TextView) findViewById(R.id.tvStatus)).setText(book.getStatus().label);
        applyStatus();
        Toast.makeText(this, R.string.detail_marked_read, Toast.LENGTH_SHORT).show();
    }

    private void applyStatus() {
        findViewById(R.id.btnMarkRead).setVisibility(book.getStatus() == Book.Status.READ ? View.GONE : View.VISIBLE);
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.confirm_delete)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.delete, (dialog, which) -> delete())
                .show();
    }

    private void delete() {
        BookRepository.get().remove(book);
        Toast.makeText(this, R.string.detail_deleted, Toast.LENGTH_SHORT).show();
        finish();
    }
}
