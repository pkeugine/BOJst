package com.example.jhw.exblockdetailapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<PoiRepo> mList;
    private onItemClickListener mItemClickListener;
    private int cateIndex;
    private int Index;

    public void updateList(ArrayList<PoiRepo> list) {
        this.mList = list;
        notifyDataSetChanged();
    }
    public void setCateIndex(int cateIndex) {
        this.cateIndex = cateIndex;
    }

    public void setIndex(int Index) {
        this.Index = Index;
    }

    private class CellViewHolder extends RecyclerView.ViewHolder {
        private TextView placeName;
        private CardView cardview;

        //private Button delBlock;

        public CellViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.place_name);
            cardview = itemView.findViewById(R.id.cardview);
           // delBlock = itemView.findViewById(R.id.del_block);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(view, getLayoutPosition());
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            default: {
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_horizontal, parent, false);
                return new CellViewHolder(v1);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            default: {
                CellViewHolder cellViewHolder = (CellViewHolder) holder;
                cellViewHolder.placeName.setText("" + mList.get(position).getName());
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public interface onItemClickListener {
        void onItemClick(View view, int position);
        //void onItemLongClick(View view, int position);
    }

    // for both short and long click
    public void setOnItemClickListener(final onItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
