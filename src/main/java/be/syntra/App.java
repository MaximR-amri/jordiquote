package be.syntra;

import com.github.ricksbrown.cowsay.Cowsay;

public class App {

  public static void main(String[] args) {

    String message = "Hello from Java!";
    String cow = "dragon";
    String[] cowArgs = new String[]{"-f", cow, "Jordi Says: " + message};
    String result = Cowsay.say(cowArgs);
    System.out.println(result);
  }
}