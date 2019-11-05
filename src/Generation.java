import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.DoubleToIntFunction;

public class Generation {
    int popultion;
    String searchString;
    int searchLength;
    double mutationRate;
    DNA[]  generation;

    int highestFitness;
    int maximumFitness;

    ArrayList<Integer> matingPool;


    public Generation(String search, int population, double mutationRate)    {
        this.searchString = search;
        this.popultion = population;
        this.mutationRate = mutationRate;
        this.highestFitness = 0;
        this.searchLength = this.searchString.length();
        this.maximumFitness = this.searchLength;
        this.generation = new DNA[this.popultion];
        for( int i = 0; i < this.popultion; i++)    {
            this.generation[i] = new DNA(this.searchLength);
        }
        this.getFitness();
    }

    public void createNewGeneration() {
        DNA[] newGeneration = new DNA[this.generation.length];
        for (int i = 0; i < this.generation.length; i++) {
            int[] parents = this.pickParents(this.matingPool);
            //System.out.println(Arrays.toString(parents));

            char[] newGenes = this.getNewChild(parents);

            DNA newChild = new DNA(newGenes);

            //newChild.printDNA(this.searchString);

            newGeneration[i] = newChild;

        }

        this.generation = newGeneration.clone();

        this.getFitness();
    }
    /*
        Creates the child dna for the next generation
        @input:
            parents array from pick parents with the index of the parents in this.generation
        @output:
            the new dna as char array

        CointFlip-Method -> for every gene a random number is created which decides if gene is taken from dad or from the mom
     */
    public char[] getNewChild(int[] parents)    {
        char[] newDna = new char[this.searchLength];
        Random rand = new Random();
        RandomCharacter randChar = new RandomCharacter();
        for ( int i = 0; i < this.searchLength; i++)   {
            double mutate = rand.nextDouble();
            if ( mutate <= this.mutationRate ) {
                newDna[i] = randChar.getRandomChar();
                continue;
            }
            double picker = rand.nextDouble();
            if ( picker < 0.5 ) {
                newDna[i] = this.generation[parents[0]].getGene(i);
            } else {
                newDna[i] = this.generation[parents[1]].getGene(i);
            }
        }
        return newDna;
    }

    public void getFitness()    {
        int maxFit = 0;
        int maxIndex = 0;
        int[] fitness = new int[this.popultion];
        for ( int i = 0; i < this.generation.length; i++)   {
            int localFitness = this.generation[i].getFitness(this.searchString);
            fitness[i] = localFitness;
            if ( localFitness > maxFit ) {
                maxFit = localFitness;
                maxIndex = i;
            }
        }
        this.highestFitness = maxFit;
        this.matingPool = new ArrayList<>();
        // TODO ist das wirklich sinnvoll ?? -> speicherplatz
        for ( int i = 0; i < fitness.length ; i++)  {
            double localFitness = (double) fitness[i] / maxFit;
            fitness[i] = (int) Math.floor(localFitness * 100);
            for ( int j = 0; j < fitness[i] ; j++ ) {
                this.matingPool.add(i);
            }
        }
        //System.out.println("Highest Fitness: Index: " + maxIndex);
        //this.generation[maxIndex].printDNA(this.searchString);
        //System.out.println(Arrays.toString(fitness));
        //System.out.println(this.matingPool.toString());
    }

    public int getHighestFitness()  {
        return this.highestFitness;
    }

    public int getMaximumFitness()  {
        return this.maximumFitness;
    }

    /*
        Picks the parents for the next generation child
        @input:
            Mating pool as array list
        @output:
            int array with index of mom and dad for the generation array
     */
    public int[] pickParents(ArrayList<Integer> matingPool)   {
        int length = matingPool.size();
        Random rand = new Random();
        int dad = Math.abs(rand.nextInt() % length);
        int mom = Math.abs(rand.nextInt() % length);
        while ( matingPool.get(mom) == matingPool.get(dad) )    mom = Math.abs(rand.nextInt() % length);
        return new int[]{matingPool.get(dad), matingPool.get(mom)};
    }

    public void printGeneration()   {
        for ( DNA d : this.generation ) {
            System.out.println("Sentence:\t" + d.getDnaAsString());
            System.out.println("Fitness:\t" + d.getFitness(this.searchString));
        }
    }
}