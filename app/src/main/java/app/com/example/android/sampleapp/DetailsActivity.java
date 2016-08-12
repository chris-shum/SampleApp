package app.com.example.android.sampleapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.com.example.android.sampleapp.data.Singleton;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    @BindView(R.id.detailViewImage)
    ImageView mDetailViewImage;
    @BindView(R.id.detailItemName)
    TextView mDetailViewItemName;
    @BindView(R.id.detailMSRP)
    TextView mDetailViewMSRP;
    @BindView(R.id.detailItemSalePrice)
    TextView mDetailItemSalePrice;
    @BindView(R.id.detailItemDescription)
    TextView mDetailItemDescription;
    @BindView(R.id.detailItemRating)
    TextView mDetailItemRating;
    @BindView(R.id.detailCardView)
    View mCardView;

    Singleton mSingleton;

    float x1, x2;

    // TODO: 8/11/16 make this page hold fragments so I can swipe instead of calling new activities.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int position = intent.getIntExtra("Position", 0);
        mCardView.setTransitionName("Settings");


        mSingleton = Singleton.getInstance();
        Picasso.with(this).load(mSingleton.getmItemArrayList().get(position).getLargeImage()).into(mDetailViewImage);
        mDetailViewItemName.setText(mSingleton.getmItemArrayList().get(position).getName());
        mDetailViewMSRP.setText("$" + mSingleton.getmItemArrayList().get(position).getMsrp());
        mDetailViewMSRP.setPaintFlags(mDetailViewMSRP.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mDetailItemSalePrice.setText("Sale Price: $" + mSingleton.getmItemArrayList().get(position).getSalePrice());
        mDetailItemDescription.setText(removeHtml(stripHtml(mSingleton.getmItemArrayList().get(position).getLongDescription())));
        if (mSingleton.getmItemArrayList().get(position).getCustomerRating() == null) {
            mDetailItemRating.setText("Item has not been rated yet.");
        } else {
            mDetailItemRating.setText("Customer Rating:\n" + mSingleton.getmItemArrayList().get(position).getCustomerRating() + "/5.0");
        }
    }

//    removes HTML from JSON
    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }

    public String removeHtml(String html) {
        html = html.replaceAll("<(.*?)\\>", "");//Removes all items in brackets
        html = html.replaceAll("<(.*?)\\\n", "");//Must be undeneath
        html = html.replaceFirst("(.*?)\\>", "");//Removes any connected item to the last bracket
        html = html.replaceAll("&nbsp;", "");
        html = html.replaceAll("&amp;", "");
        return html;
    }


//    allows for swiping through details of items
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Intent intent = getIntent();
        int position = intent.getIntExtra("Position", 0);
        switch (ev.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                x1 = ev.getX();
                break;
            case (MotionEvent.ACTION_UP):
                x2 = ev.getX();
                if (x1 < x2) {
                    if (position == 0) {
                    } else {
                        position--;
                        Intent intent1 = new Intent(DetailsActivity.this, DetailsActivity.class);
                        intent1.putExtra("Position", position);
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(DetailsActivity.this, Pair.create(mCardView, "Settings")).toBundle();
                        startActivity(intent1, bundle);
                    }
                }
                if (x1 > x2) {
                    if (position == 9) {
                    } else {
                        position++;
                        Intent intent1 = new Intent(DetailsActivity.this, DetailsActivity.class);
                        intent1.putExtra("Position", position);
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(DetailsActivity.this, Pair.create(mCardView, "Settings")).toBundle();
                        startActivity(intent1, bundle);
                    }
                }
                break;
            default:
                return super.dispatchTouchEvent(ev);

        }
        return super.dispatchTouchEvent(ev);

    }

}
