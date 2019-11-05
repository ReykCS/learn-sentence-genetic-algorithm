import java.util.Random;
import java.util.Scanner;

public class LearnSentence {
    Generation g;
    String input;
    public LearnSentence(String input)  {
        if ( input == null )
            input = this.getStringFromConsole();
        this.g = new Generation(input.toLowerCase(), 1000, 0.01);
        this.input = input.toLowerCase();
        //System.out.println(this.g.getHighestFitness());
    }

    public LearnSentence(String input, int population)  {
        if ( input == null )
            input = this.getStringFromConsole();
        this.g = new Generation(input.toLowerCase(), population, 0.01);
        this.input = input.toLowerCase();
        //System.out.println(this.g.getHighestFitness());
    }

    public int learn(int maxGeneration) {
        int generation = 0;
        while ( g.getHighestFitness() < g.getMaximumFitness() ) {
            if ( generation >= maxGeneration ) return 0;
            g.createNewGeneration();
            generation++;

        }
        System.out.println("\t\tSentence: " + this.input);
        System.out.println(generation + "\t\tNeeded");
        return generation;
    }

    public String getStringFromConsole()    {
        System.out.println("Input a String: ");
        Scanner scan = new Scanner(System.in);
        String searchString = scan.nextLine().toLowerCase();
        System.out.println("Your input string was: " + searchString);
        return searchString;
    }
    public static void main(String[] args)   {
        /*System.out.println("Input a String: ");
        Scanner scan = new Scanner(System.in);
        String searchString = scan.nextLine().toLowerCase();
        System.out.println("Your input string was: " + searchString);
*/
        String searchString = "";
        /*DNA dna = new DNA(searchString.length());
        System.out.println(dna.getDnaAsString());

        System.out.println(dna.getFitness(searchString));
*/
        //Generation g = new Generation(searchString, 50, 0.01);
        //g.printGeneration();

        //g.createNewGeneration();

        //g.getFitness();
        long start = System.nanoTime();

        LearnSentence learn = new LearnSentence("To be or not to be this is the question.");
        learn.learn(10000);
        System.out.println(String.format("%.1f\t\tseconds", (float) (System.nanoTime() - start) / 1000000000));

    }
}