package com.example.reto_android__vibe_coding;

import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName("q") // Anotación para mapear el campo 'q' del JSON a esta variable
    private String quoteText;

    @SerializedName("a") // Anotación para mapear el campo 'a' del JSON a esta variable
    private String author;

    // Getters
    public String getQuoteText() {
        return quoteText;
    }

    public String getAuthor() {
        return author;
    }

    // Setters (opcionales, pero buenos para la completitud)
    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
