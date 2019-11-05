import java.util.ArrayList;
import java.util.Arrays;

public class HeatMap {
    int nano;
    int runs;
    int timeout;
    int maximumLength;
    int step;
    public HeatMap()    {
        this.nano = 1000000000;
        this.runs = 10;
        this.timeout = 5000;
        this.step = 5;
        this.maximumLength = 40;
    }

    public int[] getHeatArrayForPopulation(int population) {

        int[] heat = new int[this.maximumLength / this.step];
        for ( int i = 5; i <= this.maximumLength; i += this.step)    {
            heat[i/5 - 1] = getHeatForPopulationAndLength(population, i);
        }
        return heat;
    }

    public int getHeatForPopulationAndLength(int population, int length)  {
        String testString = this.createRandomString(length);
        int sum = 0;
        for ( int i = 0; i < this.runs; i++)    {
            LearnSentence learn = new LearnSentence(testString, population);
            int timeout = learn.learn(this.timeout);
            sum += timeout == 0 ? this.timeout : timeout;
        }
        int average = (sum / this.runs);
        System.out.println(average + " generations in average");
        return average;
    }

    public String createRandomString(int length)   {
        char[] newString = new char[length];
        RandomCharacter rand = new RandomCharacter();
        for ( int i = 0; i < length; i++)   {
            newString[i] = rand.getRandomChar();
        }
        return new String(newString);
    }

    public static void main(String[] args)   {
        HeatMap map = new HeatMap();

        int[] heat = map.getHeatArrayForPopulation(1000);

        System.out.println(Arrays.toString(heat));
    }
}
