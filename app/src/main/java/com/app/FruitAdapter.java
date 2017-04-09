package com.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context mcontext;
    private List<Fruit> mfruitList;

    public  FruitAdapter(Context context,List<Fruit> fruitList) {
     this.mcontext=context;
    this.mfruitList=fruitList;

  }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mcontext==null) {
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Fruit fruit=mfruitList.get(position+1);
                Intent intent=new Intent(mcontext,DrawerActivity.class);
                intent.putExtra(DrawerActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(DrawerActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                mcontext.startActivity(intent);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            Fruit fruit=mfruitList.get(position);
            holder.textView.setText(fruit.getName());
        Glide.with(mcontext).load(fruit.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mfruitList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;

       public ViewHolder(View itemView) {
           super(itemView);
           cardView= (CardView) itemView;
           imageView= (ImageView) itemView.findViewById(R.id.fruit_iamge);
           textView= (TextView) itemView.findViewById(R.id.fruit_name);
       }
   }
}
