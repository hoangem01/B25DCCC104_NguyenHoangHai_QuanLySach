package com.example.emptyactivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {
    EditText edtTitle, edtAuthor, edtNote;
    Spinner spCategory;
    RadioGroup rgStatus;
    RatingBar ratingBar;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtNote = findViewById(R.id.edtNote);
        spCategory = findViewById(R.id.spCategory);
        rgStatus = findViewById(R.id.rgStatus);
        ratingBar = findViewById(R.id.ratingBar);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String author = edtAuthor.getText().toString().trim();

            if (title.isEmpty() || author.isEmpty()) {
                Toast.makeText(this, "Tên sách và tác giả không được bỏ trống", Toast.LENGTH_SHORT).show();
                return;
            }

            String category = spCategory.getSelectedItem().toString();
            String note = edtNote.getText().toString();
            float rating = ratingBar.getRating();
            
            int selectedStatusId = rgStatus.getCheckedRadioButtonId();
            RadioButton rbSelected = findViewById(selectedStatusId);
            String status = rbSelected != null ? rbSelected.getText().toString() : "Chưa đọc";

            String newId = String.valueOf(System.currentTimeMillis());
            Book newBook = new Book(newId, title, author, category, status, rating, note);
            
            MainActivity.bookList.add(newBook);
            finish(); // Quay lại màn hình danh sách
        });
    }
}