package be.syntra;

import be.syntra.dao.QuoteDao;
import be.syntra.daoimpl.QuoteDaoImpl;
import be.syntra.model.Quote;
import com.github.ricksbrown.cowsay.Cowsay;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    QuoteDao quoteDao = new QuoteDaoImpl();

    Quote quote = quoteDao.getRandomQuote();

    String message = quote.getQuote();
    String author = quote.getAuthor();

    String cow = "dragon";
    String[] cowArgs = new String[]{"-f", cow, author + " Says: " + message};
    String result = Cowsay.say(cowArgs);
    System.out.println(result);

    Scanner in = new Scanner(System.in);
    System.out.println("Did you like this quote (" + quote.getId() +")? (y/n)");
    while(true){
      String response = in.nextLine();
      if(response.equalsIgnoreCase("y")){
        quoteDao.likeQuote(quote);
        break;
      } else if (response.equalsIgnoreCase("n")){
        quoteDao.dislikeQuote(quote);
        break;
      } else {
        System.out.println("Please enter y/n");
      }
    }
  }
}