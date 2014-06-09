package com.example.customcomponent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

    IndicatorView indicator;
    EditText edTotal;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indicator = (IndicatorView) findViewById(R.id.indicator);
        edTotal = (EditText) findViewById(R.id.ed_num);
        Button btnTotal = (Button) findViewById(R.id.btn_total);
        btnTotal.setOnClickListener(this);
        Button btnPrev = (Button) findViewById(R.id.btn_prev);
        btnPrev.setOnClickListener(this);
        Button btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_total:
            indicator.updateTotal(Integer.parseInt(edTotal.getText().toString()));
            break;
        case R.id.btn_next:
            indicator.next();
            break;
        case R.id.btn_prev:
            indicator.prev();
            break;
        default:
            break;
        }
        
    }

}
