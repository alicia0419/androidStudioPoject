package org.techtown.afinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Item> mItemDataList;
    int img[];
    String name[];
    String info[];
    String price[];
    int price2[];
    String imageUri[];

    public RecyclerViewAdapter(Context mcontext, List<Item> mItemDataList, int[] img, String[] name,
                               String[] info, String[] price, int[] price2, String[] imageUri) {

        this.mContext = mcontext;
        this.mItemDataList = mItemDataList;
        this.img = img;
        this.name = name;
        this.info = info;
        this.price = price;
        this.price2 = price2;
        this.imageUri = imageUri;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView3.setText(mItemDataList.get(position).getName());
        holder.textView4.setText(mItemDataList.get(position).getPrice1());
        holder.image.setImageResource(mItemDataList.get(position).getImage());

    }

    @Override
    public int getItemCount() {

        return mItemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView3;
        private TextView textView4;

        private ImageView image;
        private CardView mainCard;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);

            image = itemView.findViewById(R.id.image);
            mainCard = itemView.findViewById(R.id.mainCard);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, Order1.class);
                    intent.putExtra("image", img[getAdapterPosition()]);
                    intent.putExtra("name", name[getAdapterPosition()]);
                    intent.putExtra("info", info[getAdapterPosition()]);
                    intent.putExtra("price", price[getAdapterPosition()]);
                    intent.putExtra("price2", price2[getAdapterPosition()]);
                    intent.putExtra("imageUri", imageUri[getAdapterPosition()]);
                    mContext.startActivity(intent);

                }
            });


        }

        @Override
        public void onClick(View v) {


        }
    }

}