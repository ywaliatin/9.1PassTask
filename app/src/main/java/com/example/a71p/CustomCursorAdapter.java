package com.example.a71p;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a71p.util.Util;

public class CustomCursorAdapter extends RecyclerView.Adapter<CustomCursorAdapter.ViewHolder> {
    private Context context;
    private Cursor cursor;

    public CustomCursorAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lostTextView;
        public TextView nameTextView;
        public TextView titleTextView;
        public TextView desTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            lostTextView = itemView.findViewById(R.id.lost_text_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            desTextView = itemView.findViewById(R.id.description_text_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(cursor.getCount() - position - 1); // Reverse order

        holder.lostTextView.setText(cursor.getString(1));
        holder.nameTextView.setText(cursor.getString(2));
        holder.titleTextView.setText(cursor.getString(4));
        holder.desTextView.setText(cursor.getString(5));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
