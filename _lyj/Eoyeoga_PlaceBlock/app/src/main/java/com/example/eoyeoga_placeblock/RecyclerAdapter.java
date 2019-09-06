package com.example.eoyeoga_placeblock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Recycler_item> items;
    int item_layout;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView btn1, btn2;
        TextView tName, tStar, tPrice,tReview;
        CardView cardview;

        public ViewHolder(View itemView){
            super(itemView);
            btn1 = (ImageView)itemView.findViewById(R.id.deleteBtn);
            btn2 = (ImageView)itemView.findViewById(R.id.addBtn);
            tName = (TextView)itemView.findViewById(R.id.tname);
            tStar = (TextView)itemView.findViewById(R.id.tstar);
            tPrice = (TextView)itemView.findViewById(R.id.tprice);
            tReview = (TextView)itemView.findViewById(R.id.treview);
            cardview = (CardView)itemView.findViewById(R.id.cardview);
        }
    }

    public RecyclerAdapter(Context context, List<Recycler_item> items,int item_layout){
        this.context=context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.placeblock_item,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        final Recycler_item item = items.get(position);
        holder.tName.setText(item.getTname());
        holder.tStar.setText(item.getTstar());
        holder.tPrice.setText(item.getTprice());
        holder.tReview.setText(item.getTreview());
        holder.btn1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(context,item.getTname()+"을 삭제하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(context, item.getTname()+"을 추가하였습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
