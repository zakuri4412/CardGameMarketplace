package com.example.cardgamemarket.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cardgamemarket.R;
import com.example.cardgamemarket.RecyclerViewInterface;
import com.example.cardgamemarket.data.database.Card.CardEntity;
import com.example.cardgamemarket.model.PokemonResponse;
import com.example.cardgamemarket.viewmodel.CardViewModel;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<CardEntity> cardList = new ArrayList<>();
    private final CardViewModel cardViewModel;
    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    public CardAdapter(RecyclerViewInterface recyclerViewInterface,CardViewModel cardViewModel, Context context){
        this.cardViewModel = cardViewModel;
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
    }
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view, recyclerViewInterface);
    }


    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardEntity card = cardList.get(position);
        holder.nameTextView.setText(card.name);
        holder.priceTextView.setText("Price: $" + card.price);

        cardViewModel.fetchCardDetails(card.cardAPIId);

        cardViewModel.getApiCardDetails().observe((LifecycleOwner) context, detailsMap -> {
            Log.d("CardAdapter", "DetailsMap size: " + detailsMap.size());

            if (detailsMap.containsKey(card.cardAPIId)) {
                PokemonResponse details = detailsMap.get(card.cardAPIId);
                Log.d("CardAdapter", "Found card details: " + details);

                Glide.with(context)
                        .load(details.getData().getSmallImages())
                        .into(holder.image);
            } else {
                Log.e("CardAdapter", "No details found for ID: " + card.cardAPIId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void submitList(List<CardEntity> newList) {
        cardList = newList;
        notifyDataSetChanged();
    }

    public CardEntity getItemAt(int position) {
        return cardList.get(position);
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView;
        ImageView image;

        CardViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.cardName);
            priceTextView = itemView.findViewById(R.id.cardPrice);
            image = itemView.findViewById(R.id.card_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
