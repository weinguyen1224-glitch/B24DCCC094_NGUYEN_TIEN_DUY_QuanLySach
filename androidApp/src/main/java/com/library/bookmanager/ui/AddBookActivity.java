package com.library.bookmanager.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.library.bookmanager.R;
import com.library.bookmanager.data.Book;
import com.library.bookmanager.data.BookRepository;

public class AddBookActivity extends Activity {

    private EditText titleInput;
    private EditText authorInput;
    private Spinner genrePicker;
    private RadioGroup statusGroup;
    private RatingBar ratingBar;
    private EditText noteInput;
    private TextView ratingLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        titleInput = findViewById(R.id.etTitle);
        authorInput = findViewById(R.id.etAuthor);
        genrePicker = findViewById(R.id.spGenre);
        statusGroup = findViewById(R.id.rgStatus);
        ratingBar = findViewById(R.id.ratingBar);
        noteInput = findViewById(R.id.etNote);
        ratingLabel = findViewById(R.id.tvRating);

        genrePicker.setAdapter(spinnerAdapter(R.array.genres));
        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> showRating(rating));
        showRating(ratingBar.getRating());

        ((TextView) findViewById(R.id.tvToolbarTitle)).setText(R.string.add_title);
        findViewById(R.id.btnBack).setOnClickListener(view -> finish());
        findViewById(R.id.btnSave).setOnClickListener(view -> save());
    }

    private void save() {
        Book book = readBook();
        if (book == null) {
            return;
        }
        BookRepository.get().add(book);
        finish();
    }

    private Book readBook() {
        String title = titleInput.getText().toString().trim();
        String author = authorInput.getText().toString().trim();

        titleInput.setError(title.isEmpty() ? getString(R.string.required) : null);
        authorInput.setError(author.isEmpty() ? getString(R.string.required) : null);
        if (title.isEmpty() || author.isEmpty()) {
            return null;
        }

        return new Book(
                title,
                author,
                genrePicker.getSelectedItem().toString(),
                statusGroup.getCheckedRadioButtonId() == R.id.rbRead ? Book.Status.READ
                        : statusGroup.getCheckedRadioButtonId() == R.id.rbReading ? Book.Status.READING
                        : Book.Status.UNREAD,
                ratingBar.getRating(),
                noteInput.getText().toString().trim());
    }

    private void showRating(float rating) {
        ratingLabel.setText(getString(R.string.rating_value, (int) rating));
    }

    private ArrayAdapter<CharSequence> spinnerAdapter(int options) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, options, R.layout.item_spinner);
        adapter.setDropDownViewResource(R.layout.item_spinner);
        return adapter;
    }
}
