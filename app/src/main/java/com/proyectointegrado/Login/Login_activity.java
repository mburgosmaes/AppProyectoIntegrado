package com.proyectointegrado.Login;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.proyecto.appproyectointegrado.R;
import com.proyectointegrado.Database_manager.In_Api_Service;
import com.proyectointegrado.Database_manager.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Login_activity extends AppCompatActivity implements View.OnClickListener{
    EditText et_email_login, et_pass_login, et_user, et_email_register, et_pass_register;
    Button btn_singup, btn_login;
    ImageView img_logo;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    In_Api_Service in_api_service;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email_login = findViewById(R.id.et_user);
        et_pass_login = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_singup = findViewById(R.id.btn_singup);
        img_logo = findViewById(R.id.img_logo);
        btn_login.setOnClickListener(this);
        btn_singup.setOnClickListener(this);
        Retrofit retrofitClient = RetrofitClient.getInstance();
        in_api_service = retrofitClient.create(In_Api_Service.class);

    }

    public void mostrarToast (String mensaje) {
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
    private void loginUser (String email, String pass) {
        if(email.isEmpty() || pass.isEmpty())
        {
            mostrarToast(getResources().getString(R.string.empty_email));
        }
        else
        {
            compositeDisposable.add(in_api_service.login_user(email,pass)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String response) throws Exception {
                            mostrarToast(response);
                        }
                    })
            );
        }
    }
    private void registerUSer(String name, String surname, String email, String pass, String tpye, String date) {
        final View register_layout = LayoutInflater.from(Login_activity.this)
                .inflate(R.layout.activity_registre,null);

        new MaterialStyledDialog.Builder(Login_activity.this)
                .setIcon(R.drawable.ic_launcher_background)
                .setTitle("REGISTRO")
                .setDescription("Por favor, completa todos los campos")
                .setCustomView(register_layout)
                .setNegativeText("CANCELAR")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                      //  et_email_register = register_layout.findViewById(R.id.et_email_layout);

                    }
                }).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_login:
                loginUser(et_email_login.getText().toString(),et_pass_login.getText().toString());
                break;

            case R.id.btn_singup:
                break;
        }

    }
}