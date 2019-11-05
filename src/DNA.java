import java.util.Random;

public class DNA {
    public char[] sentence;

    public DNA(int length)  {
        sentence = new char[length];

        RandomCharacter rand = new RandomCharacter();

        for ( int i = 0; i < length; i++)   {
            sentence[i] = rand.getRandomChar();
        }
    }

    public DNA(char[] sentence) {
        this.sentence = sentence.clone();
    }

    public int getFitness(String search) {
        int fitness = 0;
        for ( int i = 0; i < this.sentence.length; i++) {
            if ( this.sentence[i] == search.charAt(i) ) fitness++;
        }
        //TODO fitness funktion anpassen und verbessern
        return fitness;
    }

    public char getGene(int index)  {
        return this.sentence[index];
    }

    public String getDnaAsString()  {
        return String.valueOf(this.sentence);
    }

    public void printDNA(String search)  {
        //System.out.println("Sentence:\t" + this.getDnaAsString());
        //System.out.println("Fitness:\t" + this.getFitness(search));
    }
}
