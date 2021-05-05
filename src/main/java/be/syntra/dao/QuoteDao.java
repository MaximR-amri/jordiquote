package be.syntra.dao;

import be.syntra.model.Quote;

public interface QuoteDao {
    Quote getRandomQuote();
    void likeQuote(Quote quote);
    void dislikeQuote(Quote quote);

}
