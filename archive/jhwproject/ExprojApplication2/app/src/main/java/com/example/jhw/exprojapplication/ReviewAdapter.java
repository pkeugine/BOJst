package com.example.jhw.exprojapplication;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View v, ContentValues values);
    }

    private ArrayList<ReviewItem> mData = null;
    private Context mContext = null;
    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    ReviewAdapter(ArrayList<ReviewItem> list,Context context) {
        mData = list;
        mContext = context;
    }
/*
    public void resetAll(ArrayList<ReviewItem> newlist) {
        this.mData = null;
        this.mData = newlist;
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView userId;
        TextView opinion;
        TextView grade;
        TextView date;
        Button delete;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            userId = itemView.findViewById(R.id.userId);
            opinion = itemView.findViewById(R.id.opinion);
            grade = itemView.findViewById(R.id.grade);
            date = itemView.findViewById(R.id.date);
            delete = itemView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        ReviewItem item = mData.get(pos);
                        ContentValues values = new ContentValues();
                        values.put("userId",item.getUserId());
                        values.put("opinion",item.getOpinion());
                        if(mListener != null) {
                            mListener.onItemClick(v,values);
                        }
                    }
                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.review_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ReviewItem item = mData.get(i);

        Glide.with(mContext).load("http://192.168.0.13:8000/mavenweb/images/"+item.getImagePath())
                .error(R.drawable.baseline_accessibility_black_36dp).into(viewHolder.image);;
        //Glide.with(mContext).load(item.getImagePath())
        //        .error(R.drawable.baseline_accessibility_black_36dp).into(viewHolder.image);;

        viewHolder.userId.setText(item.getUserId());
        viewHolder.opinion.setText(item.getOpinion());
        viewHolder.grade.setText(item.getGrade().toString());
        viewHolder.date.setText(item.getDate());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
