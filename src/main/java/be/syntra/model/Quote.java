package be.syntra.model;

import lombok.Data;

@Data
public class Quote {
    private int id;
    private String author;
    private String quote;
    private int likes;
    private int dislikes;


}
