package id.sch.smktelkom_mlg.project.xiirpl301112131.suaramoklet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by SMK Telkom SP Malang on 10-Nov-16.
 */
public class Choose extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
        findViewById(R.id.buttonSarpra).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choose.this, SarpraPanel.class));
            }
        });

        findViewById(R.id.buttonKesiswaan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choose.this, GuruPanel.class));
            }
        });
    }
}
