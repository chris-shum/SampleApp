package app.com.example.android.sampleapp.data;

import java.util.ArrayList;

import app.com.example.android.sampleapp.models.Item;

/**
 * Created by ShowMe on 8/8/16.
 */
public class Singleton {
    ArrayList<Item> mItemArrayList;

    public ArrayList<Item> getmItemArrayList() {
        if(mItemArrayList ==null){
            mItemArrayList = new ArrayList<>();
        }
        return mItemArrayList;
    }

    private static Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }
}
