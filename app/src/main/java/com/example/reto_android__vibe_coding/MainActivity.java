package com.example.reto_android__vibe_coding;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Declaración de los componentes de la UI
    private TextView quoteTextView;
    private TextView authorTextView;
    private EditText contextEditText;
    private Button newQuoteButton;
    private Button aiQuoteButton;

    private ZenQuotesApiService apiService;

    private static final String TAG = "MainActivity"; // Etiqueta para logs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicialización de los componentes de la UI
        quoteTextView = findViewById(R.id.quoteTextView);
        authorTextView = findViewById(R.id.authorTextView);
        contextEditText = findViewById(R.id.contextEditText);
        newQuoteButton = findViewById(R.id.newQuoteButton);
        aiQuoteButton = findViewById(R.id.aiQuoteButton);

        // Inicialización del servicio de Retrofit para ZenQuotes
        apiService = RetrofitClientInstance.getRetrofitInstance().create(ZenQuotesApiService.class);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar OnClickListener para el botón "Nueva frase"
        newQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchRandomQuote();
            }
        });

        // Configurar onClickListener para el botón "Motívame con IA" (Mockup)
        aiQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userContext = contextEditText.getText().toString().trim();
                if (!userContext.isEmpty()) {
                    // Simulación de respuesta de IA
                    String aiMotivationalQuote = "Aquí tienes una frase de IA especialmente para ti que estás " + userContext + ": ¡Sigue adelante, tú puedes!";
                    quoteTextView.setText("\"" + aiMotivationalQuote + "\"");
                    authorTextView.setText("- IA Motivadora (Mockup)");
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, escribe un contexto para la IA.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para obtener y mostrar una frase aleatoria de ZenQuotes
    private void fetchRandomQuote() {
        Call<List<Quote>> call = apiService.getRandomQuote();
        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    // La API devuelve un array con una sola frase
                    Quote quote = response.body().get(0);
                    quoteTextView.setText("\"" + quote.getQuoteText() + "\"");
                    authorTextView.setText("- " + quote.getAuthor());
                } else {
                    // Manejo de respuesta no exitosa
                    quoteTextView.setText("No se pudo obtener la frase de ZenQuotes.");
                    authorTextView.setText("");
                    Log.e(TAG, "Error en la respuesta de la API ZenQuotes: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                // Manejo de fallo en la petición
                quoteTextView.setText("Error al conectar con el servidor de ZenQuotes.");
                authorTextView.setText("");
                Log.e(TAG, "Fallo en la petición a la API ZenQuotes", t);
            }
        });
    }
}
