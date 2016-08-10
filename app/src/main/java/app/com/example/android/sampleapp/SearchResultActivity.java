package app.com.example.android.sampleapp;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import app.com.example.android.sampleapp.adapters.ItemTouchHelperCallback;
import app.com.example.android.sampleapp.adapters.RecyclerViewAdapter;
import app.com.example.android.sampleapp.data.Singleton;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity {

    @BindView(R.id.searchResultRecyclerView)
    RecyclerView mSearchResultRecyclerView;
    RecyclerViewAdapter mRecyclerViewAdapter;
    Singleton mSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        mSingleton = Singleton.getInstance();
        mRecyclerViewAdapter = new RecyclerViewAdapter(mSingleton.getmItemArrayList(), this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        if (width > 1500) {
            mSearchResultRecyclerView.setLayoutManager(gridLayoutManager);
        } else {

            mSearchResultRecyclerView.setLayoutManager(linearLayoutManager);
        }
        mSearchResultRecyclerView.setAdapter(mRecyclerViewAdapter);

        ItemTouchHelper.Callback callback =
                new ItemTouchHelperCallback(mRecyclerViewAdapter, mRecyclerViewAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mSearchResultRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Intent intent = new Intent(SearchResultActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SearchResultActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
