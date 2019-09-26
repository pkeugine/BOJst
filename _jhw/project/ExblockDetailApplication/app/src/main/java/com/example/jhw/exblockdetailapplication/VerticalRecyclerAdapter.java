package com.example.jhw.exblockdetailapplication;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerticalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<HorizonRepo> mList;
    private Context mContext;
    private OnClickListener mClickListener;
    private OnClick2Listener mClickListener2;
    private RecyclerView.RecycledViewPool viewPool;


    public VerticalRecyclerAdapter(ArrayList<HorizonRepo> list) {
        this.mList = list;
        viewPool = new RecyclerView.RecycledViewPool();
    }


    private class CellViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private Button addBlock;
        private Button delBlock;
        private LinearLayoutManager layoutManager;
        private HorizontalRecyclerAdapter adapter;

        public CellViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.horizontal_recycler_view);
            addBlock = itemView.findViewById(R.id.add_block);
            addBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick(view, getLayoutPosition()); // onItemClick 실행 ( 메인액티비티에 정의 )
                    }
                }
            });
            delBlock = itemView.findViewById(R.id.del_block);
            delBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick(view, getLayoutPosition()); // onItemClick 실행 ( 메인액티비티에 정의 )
                    }
                }
            });
            layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            adapter = new HorizontalRecyclerAdapter();
            adapter.setOnItemClickListener(new HorizontalRecyclerAdapter.onItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    HorizonRepo hr = mList.get(getLayoutPosition());
                    hr.sethIndex(position);
                    hr.setvIndex(getLayoutPosition());
                    mClickListener2.onItem2Click(hr);
                }
            });
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setRecycledViewPool(viewPool);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setAdapter(adapter);

            StartSnapHelper snapHelper = new StartSnapHelper();
            snapHelper.attachToRecyclerView(mRecyclerView);

        }

        public void setData(ArrayList<PoiRepo> list) {
            adapter.updateList(list);
        }

        public void setPosition(int position) { mRecyclerView.smoothScrollToPosition(position);}
        public void setCate(int cate) { adapter.setCateIndex(cate); }
        //public void setIndex(int index) { adapter.setIndex(index); }

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        switch (viewType) {
            default: {
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_vertical, parent, false);
                return new CellViewHolder(v1);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            default: {
                CellViewHolder cellViewHolder = (CellViewHolder) holder;
                if(position==0) {
                    cellViewHolder.delBlock.setVisibility(View.GONE);
                    break;
                }
                cellViewHolder.setData(mList.get(position).getPoiList()); // 수평 데이터를 set
                cellViewHolder.setPosition(mList.get(position).gethIndex());

                //cellViewHolder.setIndex(position);
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




    public void setOnClick2Listener(final OnClick2Listener mClickListener) { this.mClickListener2 = mClickListener; }
    public interface OnClick2Listener { void onItem2Click(HorizonRepo hr); }


    // onItemClick 함수를 갖는 OnClickListener를 받음
    public void setOnClickListener(final OnClickListener mClickListener) { this.mClickListener = mClickListener; }
    public interface OnClickListener { void onItemClick(View view, int position); }

}
