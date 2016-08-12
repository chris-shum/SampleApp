package app.com.example.android.sampleapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.com.example.android.sampleapp.data.Singleton;
import app.com.example.android.sampleapp.data.WalmartAPI;
import app.com.example.android.sampleapp.models.Item;
import app.com.example.android.sampleapp.models.Walmart;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainImageView)
    ImageView mMainImageView;
    @BindView(R.id.mainTextView)
    TextView mMainTextView;
    @BindView(R.id.mainEditText)
    EditText mMainEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        Picasso.with(this).load(R.drawable.mlynch).into(mMainImageView);

//        API call happens after press of fab, checks set, if all pass, gets data, goes to next activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMainEditText.getText().toString().equals("")) {
                    mMainEditText.setError(getString(R.string.error_message));
                } else {

                    if (isNetworkAvailable(getApplicationContext())) {
                        WalmartAPI.Factory.getInstance().getWalmartItem(mMainEditText.getText().toString(), getString(R.string.API_key)).enqueue(new Callback<Walmart>() {
                            @Override
                            public void onResponse(Call<Walmart> call, Response<Walmart> response) {
                                List<Item> APIResponse = response.body().getItems();
                                if (APIResponse.size() != 0) {
                                    Singleton singleton = Singleton.getInstance();
                                    singleton.getmItemArrayList().clear();
                                    singleton.getmItemArrayList().addAll(APIResponse);
                                    Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, getString(R.string.no_results) + mMainEditText.getText().toString() + ".", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Walmart> call, Throwable t) {
                                Toast.makeText(MainActivity.this, getString(R.string.call_error), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(MainActivity.this, getString(R.string.network_down), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}