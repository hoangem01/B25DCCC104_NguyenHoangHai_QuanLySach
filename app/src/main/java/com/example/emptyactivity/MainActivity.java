package com.example.emptyactivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Book> bookList = new ArrayList<>();
    BookAdapter adapter; 
    ListView lvBooks;
    TextView tvTotal, tvRead, tvUnread;
    Button btnAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Nạp dữ liệu mẫu nếu danh sách trống
        if(bookList.isEmpty()) {
            bookList.add(new Book("1", "Lập trình Android cơ bản", "Nguyễn Văn A", "Công nghệ", "Đang đọc", 4.0f, ""));
            bookList.add(new Book("2", "Đắc nhân tâm", "Dale Carnegie", "Kỹ năng sống", "Đã đọc", 5.0f, ""));
            bookList.add(new Book("3", "Dế Mèn phiêu lưu ký", "Tô Hoài", "Văn học", "Chưa đọc", 0.0f, ""));
        }

        tvTotal = findViewById(R.id.tvTotal);
        tvRead = findViewById(R.id.tvRead);
        tvUnread = findViewById(R.id.tvUnread);
        lvBooks = findViewById(R.id.lvBooks);
        btnAddBook = findViewById(R.id.btnAddBook);

        adapter = new BookAdapter(this, bookList);
        lvBooks.setAdapter(adapter);

        updateStats();

        // Mở AddBookActivity
        btnAddBook.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        // Mở BookDetailActivity, truyền index qua Intent
        lvBooks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra("BOOK_INDEX", position);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        updateStats();
    }

    private void updateStats() {
        int total = bookList.size();
        int read = 0;
        for (Book b : bookList) {
            if (b.getStatus().equals("Đã đọc")) read++;
        }
        tvTotal.setText(String.valueOf(total));
        tvRead.setText(String.valueOf(read));
        tvUnread.setText(String.valueOf(total - read));
    }
}