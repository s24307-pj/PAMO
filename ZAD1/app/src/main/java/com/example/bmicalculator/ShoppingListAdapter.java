package com.example.bmicalculator;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingItemViewHolder> {

    private List<ShoppingItem> shoppingItems;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ShoppingListAdapter(List<ShoppingItem> shoppingItems, OnItemClickListener listener) {
        this.shoppingItems = shoppingItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShoppingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false);
        return new ShoppingItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingItemViewHolder holder, int position) {
        ShoppingItem item = shoppingItems.get(position);
        holder.textViewItemName.setText(item.getName());
        holder.textViewRecipeSource.setText(item.getRecipeSource());
        holder.checkboxPurchased.setChecked(item.isPurchased());
        
        if (item.isPurchased()) {
            holder.textViewItemName.setPaintFlags(holder.textViewItemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.textViewItemName.setPaintFlags(holder.textViewItemName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
        
        holder.checkboxPurchased.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public void removeCompletedItems() {
        for (int i = shoppingItems.size() - 1; i >= 0; i--) {
            if (shoppingItems.get(i).isPurchased()) {
                shoppingItems.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    static class ShoppingItemViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkboxPurchased;
        TextView textViewItemName;
        TextView textViewRecipeSource;

        ShoppingItemViewHolder(@NonNull View itemView) {
            super(itemView);
            checkboxPurchased = itemView.findViewById(R.id.checkboxPurchased);
            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            textViewRecipeSource = itemView.findViewById(R.id.textViewRecipeSource);
        }
    }
} 