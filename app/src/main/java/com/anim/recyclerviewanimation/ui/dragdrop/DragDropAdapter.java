package com.anim.recyclerviewanimation.ui.dragdrop;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.anim.recyclerviewanimation.R;

import java.util.ArrayList;
import java.util.Collections;

public class DragDropAdapter extends RecyclerView.Adapter<DragDropAdapter.ViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {
    private ArrayList<String> data;

    public DragDropAdapter(ArrayList<String> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onRowMoved(int toPosition, int fromPosition) {
        if(fromPosition<toPosition){
            for(int i=fromPosition; i<toPosition; i++){
                Collections.swap(data,i,i+1);
            }
        }else {
            for(int i=fromPosition; i>toPosition; i--){
                Collections.swap(data, i, i-1);
            }
        }

        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onRowSelected(ViewHolder myViewHolder) {
        myViewHolder.txtTitle.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(ViewHolder myViewHolder) {
        myViewHolder.txtTitle.setBackgroundColor(Color.WHITE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}