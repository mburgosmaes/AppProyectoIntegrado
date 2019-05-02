package com.proyectointegrado.Database_manager;

import io.reactivex.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface In_Api_Service {

    @POST("https://proyecto-studium.herokuapp.com/api/users/setUser")
    @FormUrlEncoded
    Observable<String> registerUser(
            @Field("Nombre") String name_user,
            @Field("Apellidos") String surname_user,
            @Field("Email") String email_user,
            @Field("Contraseña") String pass_user,
            @Field("Tipo_Usuario") String type_user);


    @POST("login")
    @FormUrlEncoded
    Observable<String> login_user(
            @Field("email") String email_user,
            @Field("pass") String pass_user);
}
