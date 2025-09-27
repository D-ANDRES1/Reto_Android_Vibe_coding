package com.example.reto_android__vibe_coding;

import java.util.List; // Importante para manejar la respuesta JSON, que es un array
import retrofit2.Call;
import retrofit2.http.GET;

public interface ZenQuotesApiService {

    // Define el endpoint para obtener una frase aleatoria.
    // La API de ZenQuotes devuelve un array JSON con un solo objeto,
    // por lo que esperamos una List<Quote>.
    @GET("random")
    Call<List<Quote>> getRandomQuote();
}
