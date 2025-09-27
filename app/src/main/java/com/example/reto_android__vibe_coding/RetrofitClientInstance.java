package com.example.reto_android__vibe_coding;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    // URL base de la API de ZenQuotes
    private static final String BASE_URL = "https://zenquotes.io/api/";

    // Método público estático para obtener la instancia de Retrofit
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            // Construye la instancia de Retrofit si aún no existe
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    // Agrega el conversor Gson para parsear las respuestas JSON
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
