package com.example.a71p;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a71p.model.user;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<user> {
    private Context context;
    private List<user> userList;

    public UserListAdapter(Context context, List<user> userList) {
        super(context, 0, userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("UserListAdapter", "getView called for position: " + position);

        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
            holder = new ViewHolder();
            holder.tvName = view.findViewById(R.id.name_text_view);
            holder.tvPhoneNumber = view.findViewById(R.id.title_text_view);
            holder.tvDescription = view.findViewById(R.id.description_text_view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        user user = userList.get(position);
        holder.tvName.setText(user.getName());
        holder.tvPhoneNumber.setText(user.getPhonenumber());
        holder.tvDescription.setText(user.getDescription());

        return view;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvPhoneNumber;
        TextView tvDescription;
    }
}

