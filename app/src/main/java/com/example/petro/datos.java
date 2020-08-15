package com.example.petro;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
public class datos extends Activity  {
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    RadioGroup rg1, rg2, rg3;
    Button btactual;
    final Context context = datos.this;
    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        //MainActivity.consumirServicio(this);
        setResult(RESULT_CANCELED);
        setContentView(R.layout.datos);
        Intent intentOrigen = getIntent();
        Bundle params = intentOrigen.getExtras();
        if (params != null) {
            mAppWidgetId = params.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        rg3 = (RadioGroup) findViewById(R.id.rg3);
        btactual = (Button) findViewById(R.id.btactual);
    }
    public void onClickButton(View v){
        SharedPreferences prefConfig = context.getSharedPreferences("Config"+String.valueOf(mAppWidgetId),  context.MODE_PRIVATE);
        SharedPreferences.Editor editorConfig = prefConfig.edit();
            switch (rg1.getCheckedRadioButtonId()) {
                case -1:
                    Toast.makeText(datos.this,"No hay Seleccionado", Toast.LENGTH_SHORT).show();
                break;
                case R.id.rbBTC1:
                    editorConfig.putString("MONEDA","BTC");
                break;
                case R.id.rbPTR1:
                    editorConfig.putString("MONEDA","PTR");
                break;
                case R.id.rbDASH1:
                    editorConfig.putString("MONEDA","DASH");
                break;
                case R.id.rbETH1:
                    editorConfig.putString("MONEDA","ETH");
                break;
                case R.id.rbLTC1:
                    editorConfig.putString("MONEDA","LTC");
                 break;
            }
            switch (rg2.getCheckedRadioButtonId()) {
                case -1:
                    Toast.makeText(datos.this,"No hay Seleccionado", Toast.LENGTH_SHORT).show();
                break;
                case R.id.rbBTC2:
                    editorConfig.putString("MONEDA2","BTC");
                break;
                case R.id.rbPTR2:
                    editorConfig.putString("MONEDA2","PTR");
                break;
                case R.id.rbDASH2:
                    editorConfig.putString("MONEDA2","DASH");
                break;
                case R.id.rbETH2:
                    editorConfig.putString("MONEDA2","ETH");
                break;
                case R.id.rbLTC2:
                    editorConfig.putString("MONEDA2","LTC");
                break;
            }
            switch (rg3.getCheckedRadioButtonId()) {
                case -1:
                    Toast.makeText(datos.this,"No hay Seleccionado", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rbBTC3:
                    editorConfig.putString("MONEDA3","BTC");
                break;
                case R.id.rbPTR3:
                    editorConfig.putString("MONEDA3","PTR");
                break;
                case R.id.rbDASH3:
                    editorConfig.putString("MONEDA3","DASH");
                break;
                case R.id.rbETH3:
                    editorConfig.putString("MONEDA3","ETH");
                break;
                case R.id.rbLTC3:
                    editorConfig.putString("MONEDA3","LTC");
                break;
            }
            editorConfig.commit();
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            widgetpetro.updateAppWidget(context, appWidgetManager, mAppWidgetId);
            Intent resultado = new Intent(context, widgetpetro.class);
            resultado.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK,resultado);
            finish();
        }
    public void onNocturno(View v){
        SharedPreferences prefConfig = context.getSharedPreferences("Config"+String.valueOf(mAppWidgetId), context.MODE_PRIVATE);
        SharedPreferences.Editor editorConfig = prefConfig.edit();
        editorConfig.putBoolean("NOCTURNO", true);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(datos.this);
        widgetpetro.modoNocturno(context, appWidgetManager, mAppWidgetId);
        editorConfig.commit();
        }
    public void onDia(View v){
        SharedPreferences prefConfig = context.getSharedPreferences("Config"+String.valueOf(mAppWidgetId), context.MODE_PRIVATE);
        SharedPreferences.Editor editorConfig = prefConfig.edit();
        editorConfig.putBoolean("NOCTURNO", false);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(datos.this);
        widgetpetro.modoClaro(context, appWidgetManager, mAppWidgetId);
        editorConfig.commit();
        }
}
