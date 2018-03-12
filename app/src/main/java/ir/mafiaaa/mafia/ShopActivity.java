package ir.mafiaaa.mafia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class ShopActivity extends AppCompatActivity {

    FrameLayout coin1 ;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ll = (LinearLayout) findViewById(R.id.shop_layout);
        coin1 = (FrameLayout) findViewById(R.id.shop_1coin);

    }
}
