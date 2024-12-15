package com.example.telecommanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ErrorHistoryAdapter extends RecyclerView.Adapter<ErrorHistoryAdapter.ErrorViewHolder> {

    private List<String> errorHistory;

    public ErrorHistoryAdapter(List<String> errorHistory) {
        this.errorHistory = errorHistory;
    }

    @Override
    public ErrorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error_history, parent, false);
        return new ErrorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ErrorViewHolder holder, int position) {
        String error = errorHistory.get(position);
        holder.tvError.setText(error);
    }

    @Override
    public int getItemCount() {
        return errorHistory.size();
    }

    public void updateData(List<String> newErrorHistory) {
        this.errorHistory = newErrorHistory;
        notifyDataSetChanged();
    }

    public static class ErrorViewHolder extends RecyclerView.ViewHolder {
        TextView tvError;

        public ErrorViewHolder(View itemView) {
            super(itemView);
            tvError = itemView.findViewById(R.id.tvError);
        }
    }
}
