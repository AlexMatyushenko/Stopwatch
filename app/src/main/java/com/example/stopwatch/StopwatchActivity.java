package com.example.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {

    //Количество секунд на секундомере.
    private int seconds = 0;
    //Секундомер работает?
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        runTimer();
    }

    //Запустить секундомер по нажатию кнопки START
    public void onClickStart(View view) {
        running = true;
    }

    //Остановть секундомер по нажатию кнопки STOP
    public void onClickStop(View view) {
        running = false;
    }

    //Сбросить секундомер по нажатию кнопки RESET
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    //Обновление показаний таймера
    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }

        });
    }
}
