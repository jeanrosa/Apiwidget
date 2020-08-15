package com.example.petro;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.Toast;
/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link widgetpetroConfigureActivity widgetpetroConfigureActivity}
 */
public class widgetpetro extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        MainActivity.consumirServicio(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widgetpetro);
        SharedPreferences prefMonedas = context.getSharedPreferences("Monedas", context.MODE_PRIVATE);
        SharedPreferences prefConfig = context.getSharedPreferences("Config"+String.valueOf(appWidgetId), context.MODE_PRIVATE);
        String moneda = prefConfig.getString("MONEDA","");
        String moneda2 = prefConfig.getString("MONEDA2", "");
        String moneda3 = prefConfig.getString("MONEDA3", "");
        boolean nocturno = prefConfig.getBoolean("NOCTURNO", false);
        views.setTextViewText(R.id.txPTRfijo, "PTR: " + prefMonedas.getString("PTRBS", "..." ) + " Bs");
        views.setTextViewText(R.id.txUSDfijo, "USD: " + prefMonedas.getString("USDBS", "...") + " Bs");
        if (moneda != ""){
            switch (moneda){
                case "PTR":
                    views.setTextViewText(R.id.txMoneda, "PTR");
                    views.setTextViewText(R.id.txPrecio, "USD: " + prefMonedas.getString("PTRUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("PTREUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda, R.drawable.petroicon);
                break;
                case "BTC":
                    views.setTextViewText(R.id.txMoneda, "BTC");
                    views.setTextViewText(R.id.txPrecio, "USD: " + prefMonedas.getString("BTCUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("BTCEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda, R.drawable.btcicon);
                break;
                case "ETH":
                    views.setTextViewText(R.id.txMoneda, "ETH");
                    views.setTextViewText(R.id.txPrecio, "USD: " + prefMonedas.getString("ETHUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("ETHEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda, R.drawable.etcicon);
                break;
                case "DASH":
                    views.setTextViewText(R.id.txMoneda, "DASH");
                    views.setTextViewText(R.id.txPrecio, "USD: " + prefMonedas.getString("DASHUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("DASHEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda, R.drawable.etcicon);
                    break;
                case "LTC":
                    views.setTextViewText(R.id.txMoneda, "LTC");
                    views.setTextViewText(R.id.txPrecio, "USD: " + prefMonedas.getString("LTCUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("LTCEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda, R.drawable.etcicon);
                 break;
            }
            switch (moneda2){
                case "PTR":
                    views.setTextViewText(R.id.txMoneda2, "PTR");
                    views.setTextViewText(R.id.txPrecio2, "USD: " + prefMonedas.getString("PTRUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("PTREUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda2, R.drawable.petroicon);
                    break;
                case "BTC":
                    views.setTextViewText(R.id.txMoneda2, "BTC");
                    views.setTextViewText(R.id.txPrecio2, "USD: " + prefMonedas.getString("BTCUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("BTCEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda2, R.drawable.btcicon);
                    break;
                case "ETH":
                    views.setTextViewText(R.id.txMoneda2, "ETH");
                    views.setTextViewText(R.id.txPrecio2, "USD: " + prefMonedas.getString("ETHUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("ETHEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda2, R.drawable.etcicon);
                    break;
                case "DASH":
                    views.setTextViewText(R.id.txMoneda2, "DASH");
                    views.setTextViewText(R.id.txPrecio2, "USD: " + prefMonedas.getString("DASHUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("DASHEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda2, R.drawable.etcicon);
                    break;
                case "LTC":
                    views.setTextViewText(R.id.txMoneda2, "LTC");
                    views.setTextViewText(R.id.txPrecio2, "USD: " + prefMonedas.getString("LTCUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("LTCEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda2, R.drawable.etcicon);
                    break;
            }
            switch (moneda3){
                case "PTR":
                    views.setTextViewText(R.id.txMoneda3, "PTR");
                    views.setTextViewText(R.id.txPrecio3, "USD: " + prefMonedas.getString("PTRUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("PTREUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda3, R.drawable.petroicon);
                    break;
                case "BTC":
                    views.setTextViewText(R.id.txMoneda3, "BTC");
                    views.setTextViewText(R.id.txPrecio3, "USD: " + prefMonedas.getString("BTCUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("BTCEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda3, R.drawable.btcicon);
                    break;
                case "ETH":
                    views.setTextViewText(R.id.txMoneda3, "ETH");
                    views.setTextViewText(R.id.txPrecio3, "USD: " + prefMonedas.getString("ETHUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("ETHEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda3, R.drawable.etcicon);
                    break;
                case "DASH":
                    views.setTextViewText(R.id.txMoneda3, "DASH");
                    views.setTextViewText(R.id.txPrecio3, "USD: " + prefMonedas.getString("DASHUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("DASHEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda3, R.drawable.etcicon);
                    break;
                case "LTC":
                    views.setTextViewText(R.id.txMoneda3, "LTC");
                    views.setTextViewText(R.id.txPrecio3, "USD: " + prefMonedas.getString("LTCUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("LTCEUR", "..."));
                    views.setImageViewResource(R.id.imgMoneda3, R.drawable.etcicon);
                    break;
            }
        }else{
            views.setTextViewText(R.id.txMoneda, "PTR");
            views.setTextViewText(R.id.txPrecio, "USD: " + prefMonedas.getString("PTRUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("PTREUR", "..."));
            views.setImageViewResource(R.id.imgMoneda, R.drawable.petroicon);
            views.setTextViewText(R.id.txMoneda2, "BTC");
            views.setTextViewText(R.id.txPrecio2, "USD: " + prefMonedas.getString("BTCUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("BTCEUR", "..."));
            views.setImageViewResource(R.id.imgMoneda2, R.drawable.btcicon);
            views.setTextViewText(R.id.txMoneda3, "LTC");
            views.setTextViewText(R.id.txPrecio3, "USD: " + prefMonedas.getString("LTCUSD", "...") + '\n' + "EUR: " + prefMonedas.getString("LTCEUR", "..."));
            views.setImageViewResource(R.id.imgMoneda3, R.drawable.etcicon);
        }
       if (nocturno == true){
            //modoNocturno(context, appWidgetManager,appWidgetId);
           views.setInt(R.id.widget_layout, "setBackgroundColor", Color.parseColor("#66000000"));
           // views.setInt(R.id.widget_layout, "setTheme", R.style.fondoClaro);
           views.setInt(R.id.txMoneda, "setTextColor", Color.WHITE);
           views.setInt(R.id.txMoneda2, "setTextColor", Color.WHITE);
           views.setInt(R.id.txMoneda3, "setTextColor", Color.WHITE);
           views.setInt(R.id.txPrecio, "setTextColor", Color.WHITE);
           views.setInt(R.id.txPrecio2, "setTextColor", Color.WHITE);
           views.setInt(R.id.txPrecio3, "setTextColor", Color.WHITE);
           views.setInt(R.id.txPTRfijo, "setTextColor", Color.WHITE);
           views.setInt(R.id.txUSDfijo, "setTextColor", Color.WHITE);
        }else if (nocturno == false){
            //modoClaro(context, appWidgetManager,appWidgetId);
           views.setInt(R.id.widget_layout, "setBackgroundColor", Color.parseColor("#66FFFFFF"));
           views.setInt(R.id.txMoneda, "setTextColor", Color.parseColor("#23344E"));
           views.setInt(R.id.txMoneda2, "setTextColor", Color.parseColor("#23344E"));
           views.setInt(R.id.txMoneda3, "setTextColor", Color.parseColor("#23344E"));
           views.setInt(R.id.txPrecio, "setTextColor", Color.parseColor("#23344E"));
           views.setInt(R.id.txPrecio2, "setTextColor", Color.parseColor("#23344E"));
           views.setInt(R.id.txPrecio3, "setTextColor", Color.parseColor("#23344E"));
           views.setInt(R.id.txPTRfijo, "setTextColor", Color.parseColor("#23344E"));
           views.setInt(R.id.txUSDfijo, "setTextColor", Color.parseColor("#23344E"));
        }
        Intent intent = new Intent(context, datos.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
        views.setOnClickPendingIntent(R.id.buttonSetup, pendingIntent);
        //Intent intent1 = new Intent(context, widgetpetro.class);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    static void modoNocturno(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Toast toast1 = Toast.makeText(context, "Modo Nocturno Activado", Toast.LENGTH_SHORT); toast1.show();
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widgetpetro);
        views.setInt(R.id.widget_layout, "setBackgroundColor", Color.parseColor("#66000000"));
        // views.setInt(R.id.widget_layout, "setTheme", R.style.fondoClaro);
        views.setInt(R.id.txMoneda, "setTextColor", Color.WHITE);
        views.setInt(R.id.txMoneda2, "setTextColor", Color.WHITE);
        views.setInt(R.id.txMoneda3, "setTextColor", Color.WHITE);
        views.setInt(R.id.txPrecio, "setTextColor", Color.WHITE);
        views.setInt(R.id.txPrecio2, "setTextColor", Color.WHITE);
        views.setInt(R.id.txPrecio3, "setTextColor", Color.WHITE);
        views.setInt(R.id.txPTRfijo, "setTextColor", Color.WHITE);
        views.setInt(R.id.txUSDfijo, "setTextColor", Color.WHITE);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    static void modoClaro(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Toast toast1 = Toast.makeText(context, "Modo Claro Activado", Toast.LENGTH_SHORT); toast1.show();
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widgetpetro);
        views.setInt(R.id.widget_layout, "setBackgroundColor", Color.parseColor("#66FFFFFF"));
        views.setInt(R.id.txMoneda, "setTextColor", Color.parseColor("#23344E"));
        views.setInt(R.id.txMoneda2, "setTextColor", Color.parseColor("#23344E"));
        views.setInt(R.id.txMoneda3, "setTextColor", Color.parseColor("#23344E"));
        views.setInt(R.id.txPrecio, "setTextColor", Color.parseColor("#23344E"));
        views.setInt(R.id.txPrecio2, "setTextColor", Color.parseColor("#23344E"));
        views.setInt(R.id.txPrecio3, "setTextColor", Color.parseColor("#23344E"));
        views.setInt(R.id.txPTRfijo, "setTextColor", Color.parseColor("#23344E"));
        views.setInt(R.id.txUSDfijo, "setTextColor", Color.parseColor("#23344E"));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            Toast toast1 = Toast.makeText(context, "Widget Eliminado", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
    @Override
    public void onEnabled(Context context) {

    }
    //@Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        intent.getAction().equals("android.appwidget.action.APPWIDGET_UPDATE");
        //Toast toast1 = Toast.makeText(context, "habilitado", Toast.LENGTH_SHORT);
        //toast1.show();
    }
    @Override
    public void onDisabled(Context context) {
    }

}