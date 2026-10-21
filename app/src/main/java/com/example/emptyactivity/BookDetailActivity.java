package com.example.emptyactivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailActivity extends AppCompatActivity {
    TextView tvTitle, tvAuthorCat, tvStatus, tvNote;
    RatingBar ratingBar;
    Button btnMarkRead, btnBack, btnDelete;
    int bookIndex;
    Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookIndex = getIntent().getIntExtra("BOOK_INDEX", -1);
        if (bookIndex == -1) {
            finish();
            return;
        }
        
        currentBook = MainActivity.bookList.get(bookIndex);

        tvTitle = findViewById(R.id.tvTitle);
        tvAuthorCat = findViewById(R.id.tvAuthorCat);
        tvStatus = findViewById(R.id.tvStatus);
        tvNote = findViewById(R.id.tvNote);
        ratingBar = findViewById(R.id.ratingBarDetail);
        btnMarkRead = findViewById(R.id.btnMarkRead);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);

        loadData();

        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if(fromUser) currentBook.setRating(rating);
        });

        btnMarkRead.setOnClickListener(v -> {
            currentBook.setStatus("Đã đọc");
            loadData();
        });

        btnDelete.setOnClickListener(v -> {
            MainActivity.bookList.remove(bookIndex);
            finish();
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void loadData() {
        tvTitle.setText(currentBook.getTitle());
        tvAuthorCat.setText(currentBook.getAuthor() + " • " + currentBook.getCategory());
        tvStatus.setText(currentBook.getStatus());
        tvNote.setText(currentBook.getNote());
        ratingBar.setRating(currentBook.getRating());

        if (currentBook.getStatus().equals("Đã đọc")) {
            btnMarkRead.setVisibility(View.GONE); // Hoặc btnMarkRead.setEnabled(false);
        }
    }
}