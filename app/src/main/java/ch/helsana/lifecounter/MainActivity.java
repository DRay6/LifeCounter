package ch.helsana.lifecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btPlus100 =  findViewById(R.id.btPlus100);
        Button btPlus500 =  findViewById(R.id.btPlus500);
        Button btPlus1000 = findViewById(R.id.btPlus1000);
        Button btMinus100 =  findViewById(R.id.btMin100);
        Button btMinus500 =  findViewById(R.id.btMin500);
        Button btMinus1000 = findViewById(R.id.btMin1000);

    }
}