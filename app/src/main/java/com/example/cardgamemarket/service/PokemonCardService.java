package com.example.cardgamemarket.service;

import com.example.cardgamemarket.model.PokemonCard;
import com.example.cardgamemarket.model.PokemonResponse;

import retrofit2.http.*;
import retrofit2.Call;
public interface
PokemonCardService {
    @GET("v2/cards/{id}")
    Call<PokemonResponse> getPokemonCardDetail(
            @Header("X-Api-Key") String apiKey,
            @Path("id") String id
    );
}
