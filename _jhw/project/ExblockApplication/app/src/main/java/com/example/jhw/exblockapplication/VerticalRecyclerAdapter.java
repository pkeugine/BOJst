package com.example.jhw.exblockapplication;


import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.jhw.exblockapplication.MainActivity.subList;


public class VerticalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ArrayList<Integer>> mList;
    private SparseIntArray listPosition = new SparseIntArray();
    private HorizontalRecyclerAdapter.OnItemClickListener mItemClickListener;
    private Context mContext;
    private RecyclerView.RecycledViewPool viewPool;
    private OnClickListener mClickListener;

    public VerticalRecyclerAdapter(ArrayList<ArrayList<Integer>> list) {
        this.mList = list;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    private class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecyclerView mRecyclerView;
        private HorizontalRecyclerAdapter adapter;
        private LinearLayoutManager layoutManager;

        private Button mButton;


        public CellViewHolder(View itemView) {
            super(itemView);


            mButton = itemView.findViewById(R.id.add);
            mRecyclerView = itemView.findViewById(R.id.recyclerView);
            mRecyclerView.setRecycledViewPool(viewPool);

            mRecyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(layoutManager);


            adapter = new HorizontalRecyclerAdapter();
            adapter.SetOnItemClickListener(mItemClickListener);
            mRecyclerView.setAdapter(adapter);


            // this is needed if you are working with CollapsingToolbarLayout, I am adding this here just in case I forget.
            mRecyclerView.setNestedScrollingEnabled(false);

            //optional
            StartSnapHelper snapHelper = new StartSnapHelper();
            snapHelper.attachToRecyclerView(mRecyclerView);
            mButton.setOnClickListener(this);
        }

        public void setData(ArrayList<Integer> list) {
            adapter.updateList(list);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                mClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        switch (viewType) {
            default: {
                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_list_item_vertical, viewGroup, false);
                return new CellViewHolder(v1);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        switch (viewHolder.getItemViewType()) {
            default: {
                CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;

                cellViewHolder.setData(mList.get(position));

                int lastSeenFirstPosition = listPosition.get(position, 0);
                if (lastSeenFirstPosition >= 0) {
                    cellViewHolder.layoutManager.scrollToPositionWithOffset(lastSeenFirstPosition, 0);
                }
                break;
            }
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        final int position = viewHolder.getAdapterPosition();
        CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;
        int firstVisiblePosition = cellViewHolder.layoutManager.findFirstVisibleItemPosition();
        listPosition.put(position, firstVisiblePosition);

        super.onViewRecycled(viewHolder);
    }


    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }


    // for both short and long click
    public void SetOnItemClickListener(final HorizontalRecyclerAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnClickListener {
        void onItemClick(View view, int position);

    }

    // for both short and long click
    public void SetOnClickListener(final OnClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }
}