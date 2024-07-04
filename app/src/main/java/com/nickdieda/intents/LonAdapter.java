package com.nickdieda.intents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LonAdapter extends RecyclerView.Adapter<LonAdapter.LoanViewHolder> {

    private Context context;
    private List<LoanApplication> loanList;

    public LonAdapter(Context context, List<LoanApplication> loanList) {
        this.context = context;
        this.loanList = loanList;
    }

    @Override
    public LoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new LoanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LoanViewHolder holder, int position) {
        LoanApplication loan = loanList.get(position);
        holder.tvName.setText(loan.getName());
        holder.tvInstitution.setText(loan.getInstitute());
        holder.tvRegno.setText(loan.getRegno());
        holder.tvLoanAmount.setText(String.valueOf(loan.getLoanAmount()));
    }

    @Override
    public int getItemCount() {
        return loanList.size();
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