package com.example.petro;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //consumirServicio(this);
        ImageButton aceptar = (ImageButton) findViewById(R.id.buttonAceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public static void consumirServicio(Context context) {
       servicioTask servicioTask = new servicioTask(context, "https://petroapp-price.petro.gob.ve/price/");
       servicioTask.execute();
    }
}