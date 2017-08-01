package com.example.dramalho.hw2017mobiledevlab4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new ExchangeRateListFragment();

        //int mContainer = ((ViewGroup) fragment.getView().getParent()).getId();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();






    }


}

