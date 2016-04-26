package com.buptant.antcl.customwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button bt_myCustomView;
    private Button bt_roundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_myCustomView = (Button) findViewById(R.id.bt_mycustomview);
        bt_roundImage = (Button) findViewById(R.id.bt_roundimage);
        bt_myCustomView.setOnClickListener(this);
        bt_roundImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_mycustomview:
                Intent intent = new Intent(this, MyCustomViewActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_roundimage:
                Intent intent1 = new Intent(this, RoundImageActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
