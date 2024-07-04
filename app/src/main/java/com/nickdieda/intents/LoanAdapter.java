package com.nickdieda.intents;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.LoanViewHolder> {

    private Context context;
    private Cursor cursor;

    public LoanAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public LoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new LoanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LoanViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String institution = cursor.getString(cursor.getColumnIndexOrThrow("institution"));String regno = cursor.getString(cursor.getColumnIndexOrThrow("regno"));
            int loanAmount = cursor.getInt(cursor.getColumnIndexOrThrow("loanAmount"));

            holder.tvName.setText(name);
            holder.tvInstitution.setText(institution);
            holder.tvRegno.setText(regno);
            holder.tvLoanAmount.setText(String.valueOf(loanAmount));
        }
    }

    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public static class LoanViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvInstitution;
        public TextView tvRegno;
        public TextView tvLoanAmount;

        public LoanViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvInstitution = itemView.findViewById(R.id.tvInstitution);
            tvRegno = itemView.findViewById(R.id.tvRegno);
            tvLoanAmount = itemView.findViewById(R.id.tvLoanAmount);
        }
    }
}