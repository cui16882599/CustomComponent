package android.cq.custom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
    
    
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.indicator:
                startActivity(new Intent(this, IndicatorActivity.class));
                break;

            case R.id.circleImageView:
                startActivity(new Intent(this, RoundImageActivity.class));
                break;
        }
    }
}
