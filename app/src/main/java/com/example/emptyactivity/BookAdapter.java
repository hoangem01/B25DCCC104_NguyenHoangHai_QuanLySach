package com.example.emptyactivity; 

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvItemTitle);
        TextView tvAuthor = convertView.findViewById(R.id.tvItemAuthor);
        TextView tvCategory = convertView.findViewById(R.id.tvItemCategory);
        TextView tvStatus = convertView.findViewById(R.id.tvItemStatus);

        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());
        tvCategory.setText(book.getCategory());
        tvStatus.setText(book.getStatus());

        return convertView;
    }
}