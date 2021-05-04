package be.syntra;

import com.github.ricksbrown.cowsay.Cowsay;

public class App {

  public static void main(String[] args) {

    String[] cowArgs = new String[]{"-f", "Stegosaurus", "Hello from Java!"};
    String result = Cowsay.say(cowArgs);
    System.out.println(result);
  }
}