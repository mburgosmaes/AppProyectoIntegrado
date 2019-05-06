package com.proyectointegrado.Login;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.proyecto.appproyectointegrado.R;
import com.proyectointegrado.Database_manager.Users;
import com.proyectointegrado.Database_manager.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Login_activity extends AppCompatActivity implements View.OnClickListener {
    EditText et_email_login, et_pass_login, et_user, et_email_register, et_pass_register;
    Button btn_singup, btn_login;
    ImageView img_logo;
    Intent i;
    private static DefaultHttpClient client;
    private static JSONObject respJSONObject;
    private static Users users;
    private static Utils utils;
    private static String TAG = "PRUEBA";
    private static String email, pass;
    private static String ALLOK;
    private static List<Users> listUser;

    //todo: la imagen del logo redirecciónará a la página web.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email_login = findViewById(R.id.et_user);
        et_pass_login = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_singup = findViewById(R.id.btn_singup);
        //img_logo = findViewById(R.id.img_logo);
        btn_login.setOnClickListener(this);
        btn_singup.setOnClickListener(this);
        utils = new Utils();


    }

    public void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                email = et_email_login.getText().toString().trim();
                pass = et_pass_login.getText().toString().trim();
                new ExecuteTaskLoginUser().execute();

                // i = new Intent(this, .class);
                //startActivity(i);
                //else error;

                break;

            case R.id.btn_singup:
                Intent i = new Intent(this, Registre_activity.class);
                startActivity(i);
                break;
        }

    }

    private class ExecuteTaskLoginUser extends AsyncTask<String, String, String >{
        @Override
        protected String doInBackground(String... params) {

            String email2="";
            String pass2="";

            users = new Users();

            client = new DefaultHttpClient();
            respJSONObject = null;
            HttpConnectionParams.setConnectionTimeout(client.getParams(), 1000);

            try {

                HttpGet get = new HttpGet("https://proyecto-studium.herokuapp.com/api/user/getEmail/admin@admin.com");
               /* ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("Email", email));
                nameValuePairs.add(new BasicNameValuePair("Pass", pass));*/

                HttpResponse response = client.execute(get);
                String responseStr = EntityUtils.toString(response.getEntity());
                JSONObject object = new JSONObject(responseStr);
                log("ESTE ES OBJETO -------"+object.toString());
                email2 = object.getString("Email");
                log("Email:"+email2);
                //JSONArray respJSONArray = new JSONArray(object);
                //email2 = object.getString("Pass");

               /* for (int i = 0; i < object.length(); i++) {
                    respJSONObject = (JSONObject) respJSONArray.getJSONObject(i);
                   // respJSONObject = respJSONObject.getJSONObject("Email");
                    email2 = respJSONObject.getString("Email");
                   // montar(email2, "123");
                    //log(users.toString());

                    log("Email2: "+email2);
                    //pass2 = respJSONObject.getString("Pass");
                }*/

                    if (responseStr != null) {
                        log("SI");
                        ALLOK="si";
                    }

            } catch (Exception e) {
                e.getMessage();
                ALLOK="no";
            }
            //listUser = new ArrayList<Users>();
           // listUser.add(users);
            return ALLOK;
        }

        @Override
        protected void onPostExecute(String ALLOK2) {
            super.onPostExecute(ALLOK2);
            if (ALLOK2.equalsIgnoreCase("si"))
            {
                log("TODO OK");

            }
            else
            {
                log("NO OK");

            }
        }
    }

    public void log(String mensaje) {
        Log.i(TAG, mensaje);
    }
    public Users montar(String e, String p)
    {
        users.setEmail(e);
        users.setPass(p);

        return users;
    }

}
