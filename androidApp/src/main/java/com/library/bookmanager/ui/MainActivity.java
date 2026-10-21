package com.library.bookmanager.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.library.bookmanager.R;
import com.library.bookmanager.data.Book;
import com.library.bookmanager.data.BookRepository;
import com.library.bookmanager.data.Books;

import java.util.List;

public class MainActivity extends Activity implements ShelfAdapter.Listener {


    private final BookRepository repository = BookRepository.get();
    private ShelfAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ShelfAdapter(this, repository.getAll(), this);
        ((ListView) findViewById(R.id.listBooks)).setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(view ->
                startActivity(new Intent(this, AddBookActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Book> books = repository.getAll();
        adapter.notifyDataSetChanged();
        ((TextView) findViewById(R.id.tvStats)).setText(Books.stats(books));
        findViewById(R.id.tvEmpty).setVisibility(books.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onOpen(Book book) {
        startActivity(BookDetailActivity.intentOf(this, book));
    }
}

