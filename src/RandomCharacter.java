import java.util.Random;

public class RandomCharacter {
    public char getRandomChar() {
        Random random = new Random();
        String pool = "abcdefghijklmnopqrstuvwxyz. ";
        int rand = Math.abs(random.nextInt()) % 28;
        return pool.charAt(rand);
    }

}
