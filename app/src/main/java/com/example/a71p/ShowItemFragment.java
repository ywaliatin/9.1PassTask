package com.example.a71p;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a71p.data.DatabaseHelper;

public class ShowItemFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private CustomCursorAdapter cursorAdapter;
    private Cursor result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_item, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseHelper = new DatabaseHelper(getContext());
        result = databaseHelper.getallusers();

        cursorAdapter = new CustomCursorAdapter(getContext(), result);
        recyclerView.setAdapter(cursorAdapter);

        return view;
    }
}
