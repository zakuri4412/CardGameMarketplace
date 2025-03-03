package com.example.cardgamemarket.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cardgamemarket.data.database.Card.CardDAO;
import com.example.cardgamemarket.data.database.Card.CardEntity;
import com.example.cardgamemarket.data.provider.ConnectionHelper;
import com.example.cardgamemarket.model.PokemonCard;
import com.example.cardgamemarket.model.PokemonResponse;
import com.example.cardgamemarket.service.PokemonCardService;
import com.example.cardgamemarket.service.RetrofitClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CardViewModel extends AndroidViewModel {
    private CardDAO cardDAO;
    private LiveData<List<CardEntity>> topCards;

    private MutableLiveData<Map<String, PokemonResponse>> apiCardDetails = new MutableLiveData<>(new HashMap<>());
    private static final String API_KEY = "8fa419a1-226a-4c4d-8e39-1127f06519d2";

    public CardViewModel(@NonNull Application application){
        super(application);
        ConnectionHelper db = ConnectionHelper.getInstance(application);
        cardDAO = db.cardDAO();
        topCards = cardDAO.getTop5Cards();
    }

    public LiveData<List<CardEntity>> getTopCards(){
        return topCards;
    }

    public LiveData<List<CardEntity>> searchCards(String query) {
        return cardDAO.searchCards(query);
    }

    public LiveData<Map<String, PokemonResponse>> getApiCardDetails(){
        return apiCardDetails;
    }
    public void fetchCardDetails(String cardId) {
        PokemonCardService apiService = RetrofitClient.getClient().create(PokemonCardService.class);
        Call<PokemonResponse> call = apiService.getPokemonCardDetail(API_KEY, cardId);

        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<String, PokemonResponse> currentData = apiCardDetails.getValue();
                    if (currentData == null) {
                        currentData = new HashMap<>();
                    }
                    currentData.put(cardId, response.body());
                    apiCardDetails.postValue(currentData);
                } else {
                    Log.e("API_ERROR", "Response failed for ID: " + cardId);
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.e("API_FAILURE", "Lỗi kết nối: " + t.getMessage());
            }
        });
    }
}
