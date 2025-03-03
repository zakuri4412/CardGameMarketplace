package com.example.cardgamemarket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardgamemarket.data.database.Card.CardEntity;
import com.example.cardgamemarket.model.PokemonCard;
import com.example.cardgamemarket.model.PokemonResponse;
import com.example.cardgamemarket.service.PokemonCardService;
import com.example.cardgamemarket.service.RetrofitClient;
import com.example.cardgamemarket.ui.CardAdapter;
import com.example.cardgamemarket.viewmodel.CardViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements RecyclerViewInterface{

    private CardViewModel cardViewModel;
    private CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityLayout(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
        adapter = new CardAdapter(this,cardViewModel, this);
        recyclerView.setAdapter(adapter);

        cardViewModel.getTopCards().observe(this, cards -> {
            Log.d("CardList", "Size: " + cards.size());
            adapter.submitList(cards);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchCards(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                searchCards(newText);
                return false;
            }
        });
        return true;
    }

    private void searchCards(String query) {
        cardViewModel.searchCards("%" + query + "%").observe(this, cards -> {
            Log.d("SearchCards", "Results: " + cards.toString()); // In toàn bộ danh sách
            for (CardEntity card : cards) {
                Log.d("SearchCards", "Card: " + card.name); // In từng card
            }
            adapter.submitList(cards);
        });
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        CardEntity card = adapter.getItemAt(pos);
        Log.d("ItemClick", "Clicked Card: " + card.cardAPIId);
    }
}
