package ch.helsana.lifecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import ch.helsana.lifecounter.Model.LifePoints;

public class MainActivity extends AppCompatActivity {

    Button btnPlus100;
    Button btnPlus500;
    Button btnPlus1000;
    Button btnMinus100;
    Button btnMinus500;
    Button btnMinus1000;
    Button btReset;
    TextView textView;
    LifePoints lifePoints;
    ProgressBar progressBar;

    @Override
    protected void onResume() {
        super.onResume();
        updateLifePoints();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlus100 = findViewById(R.id.btPlus100);
        btnPlus500 = findViewById(R.id.btPlus500);
        btnPlus1000 = findViewById(R.id.btPlus1000);
        btnMinus100 = findViewById(R.id.btMin100);
        btnMinus500 = findViewById(R.id.btMin500);
        btnMinus1000 = findViewById(R.id.btMin1000);

        btReset = findViewById(R.id.btReset);
        textView = findViewById(R.id.activity_lifePoints_text);
        progressBar = findViewById(R.id.progressBar);

        lifePoints = LifePoints.getSingletonInstance();

        updateLifePoints();

        btnPlus100.setOnClickListener(btn -> {
            lifePoints.modifyLifePoints(100);
            updateLifePoints();

        });

        btnPlus500.setOnClickListener(btn -> {
            lifePoints.modifyLifePoints(500);
            updateLifePoints();

        });

        btnPlus1000.setOnClickListener(btn -> {
            lifePoints.modifyLifePoints(1000);
            updateLifePoints();
        });

        btnMinus100.setOnClickListener(btn -> {
            lifePoints.modifyLifePoints(-100);
            updateLifePoints();
        });

        btnMinus500.setOnClickListener(btn -> {
            lifePoints.modifyLifePoints(-500);
            updateLifePoints();
        });

        btnMinus1000.setOnClickListener(btn -> {
            lifePoints.modifyLifePoints(-1000);
            updateLifePoints();
        });

        btReset.setOnClickListener(btn -> {
            lifePoints.resetLifePoints();
            updateLifePoints();
        });

        Context context = MainActivity.this;

        Intent notificationIntent = new Intent(context, NotificationSender.class);
        PendingIntent contentIntent = PendingIntent.getService(context, 0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        System.out.println("LOG");

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.cancel(contentIntent);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + AlarmManager.INTERVAL_FIFTEEN_MINUTES, AlarmManager.INTERVAL_FIFTEEN_MINUTES, contentIntent);


    }

    private void updateLifePoints(){
        textView.setText(String.valueOf(lifePoints.getLp()));
        progressBar.setProgress(lifePoints.getLp());

    }
}