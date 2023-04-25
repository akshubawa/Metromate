package com.example.amigos.metromate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OffersAdaptor extends RecyclerView.Adapter<OffersAdaptor.ViewHolder> {
    Context context;
    ArrayList<OffersCardModel> arrOffers;

    OffersAdaptor(Context context, ArrayList<OffersCardModel> arrOffers) {
        this.context = context;
        this.arrOffers = arrOffers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.offers_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.offers_post.setText(arrOffers.get(position).getOffer());
        holder.offers_heading.setText(arrOffers.get(position).getHeading());

    }

    @Override
    public int getItemCount() {

        return arrOffers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView offers_post, offers_heading;

        public ViewHolder(View itemView) {
            super(itemView);

            offers_post = itemView.findViewById(R.id.offers_post);
            offers_heading = itemView.findViewById(R.id.offers_heading);
        }
    }
}



