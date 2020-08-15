package com.example.petro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

//Clase ServicioTask
public class servicioTask extends AsyncTask<Void, Void, String> {
    public Context httpContext;
    public String resultadoapi="";
    public String linkrequestApi = "";
    public String precioUsdPtr, precioEurPtr, precioUsdBtc, precioBSPtr, precioEurBtc, precioUsdLtc, precioEurLtc, precioUsdDash, precioEurDash, precioUsdEth, precioEurEth;
    public  double precioUsd;
    //Metodo Constructor Constructora
    public servicioTask(Context context, String linkApi){
        this.httpContext=context;
        this.linkrequestApi=linkApi;
    }
    //Metodo Asincrono para conectar con la API
    @Override
    protected String doInBackground(Void... params){
        String result = null;
        String wsURL = linkrequestApi;
        URL url = null;
        String parametrosPost = "{\"coins\": [\"PTR\", \"BTC\", \"LTC\",\"DASH\",\"ETH\"],\"fiats\": [\"USD\",\"EUR\",\"BS\"]}";
        try {
            //se crea la conexion al api PetroWidget
            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setDoOutput(true);

           //Enviar Post al API
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(parametrosPost);
            writer.flush();
            writer.close();
            os.close();
            //Traer los datos
            int responseCode=urlConnection.getResponseCode();//conexion ok
            if(responseCode==HttpsURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String linea = "";
                while ((linea=in.readLine())!= null){
                    sb.append(linea);
                    break;
                }
                in.close();
                result=sb.toString();
            }
            else{
                result = new String("Error: "+responseCode);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    //Metodo que ejecuta y setea los datos de la widget
    @SuppressLint("DefaultLocale")
    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        resultadoapi=s;
        String pattern = "###,###,###,###.##";
        DecimalFormat formateador = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.GERMANY));
        if (resultadoapi != null) {
            try {
                SharedPreferences pref = httpContext.getSharedPreferences("Monedas", httpContext.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                JSONObject jsonObject = new JSONObject(s);
                JSONObject data = jsonObject.getJSONObject("data");
                JSONObject ptr = data.getJSONObject("PTR");
                     precioUsdPtr = ptr.getString("USD");
                     editor.putString("PTRUSD", formateador.format(Double.parseDouble(precioUsdPtr)));
                     precioEurPtr = ptr.getString("EUR");
                     editor.putString("PTREUR", formateador.format(Double.parseDouble(precioEurPtr)));
                     precioBSPtr =  ptr.getString("BS");
                     editor.putString("PTRBS", formateador.format(Double.parseDouble(precioBSPtr)));
                     precioUsd = Double.parseDouble(precioBSPtr)/Double.parseDouble(precioUsdPtr);
                     editor.putString("USDBS",formateador.format(precioUsd));
                JSONObject btc = data.getJSONObject("BTC");
                     precioUsdBtc = btc.getString("USD");
                     editor.putString("BTCUSD", formateador.format(Double.parseDouble(precioUsdBtc)));
                     precioEurBtc = btc.getString("EUR");
                     editor.putString("BTCEUR", formateador.format(Double.parseDouble(precioEurBtc)));
                JSONObject ltc = data.getJSONObject("LTC");
                     precioUsdLtc = ltc.getString("USD");
                     editor.putString("LTCUSD", formateador.format(Double.parseDouble(precioUsdLtc)));
                     precioEurLtc = ltc.getString("EUR");
                     editor.putString("LTCEUR", formateador.format(Double.parseDouble(precioEurLtc)));
                JSONObject dash = data.getJSONObject("DASH");
                     precioUsdDash = dash.getString("USD");
                     editor.putString("DASHUSD", formateador.format(Double.parseDouble(precioUsdDash)));
                     precioEurDash = dash.getString("EUR");
                     editor.putString("DASHEUR", formateador.format(Double.parseDouble(precioEurDash)));
                JSONObject eth = data.getJSONObject("ETH");
                     precioUsdEth = eth.getString("USD");
                     editor.putString("ETHUSD", formateador.format(Double.parseDouble(precioUsdEth)));
                     precioEurEth = eth.getString("EUR");
                     editor.putString("ETHEUR", formateador.format(Double.parseDouble(precioEurEth)));
                     editor.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            //Toast.makeText(httpContext, "problemas con el servidor", Toast.LENGTH_LONG).show();
        }
        //bolivar = Double.parseDouble(precioBSPtr) * 1000;
      }
}
