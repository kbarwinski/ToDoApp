package pl.barwinski.todoapp.shared.helpers;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RNGenerator {
    private static final String[] adjectives = {
            "quick", "slow", "happy", "sad", "angry", "calm", "bright", "dim",
            "sharp", "dull", "hot", "cold", "long", "short", "loud", "quiet"
    };

    private static final String[] verbs = {
            "jump", "run", "swim", "climb", "dance", "sing", "write", "read",
            "cook", "paint", "draw", "play", "work", "rest", "drive", "walk"
    };

    private static final String[] categories = {
            "Home", "Work", "School", "Shopping", "Other"
    };

    private static final String[] attachmentTypes = {
            "image", "video", "audio", "file"
    };

    private static final String[] animals = {
            "cat", "dog", "bird", "fish", "hamster", "rabbit", "mouse", "rat",
            "snake", "lizard", "turtle", "frog", "spider", "scorpion", "ant", "bee"
    };

    private static final String[] colors = {
            "red", "orange", "yellow", "green", "blue", "purple", "pink", "brown",
            "black", "white", "gray", "silver", "gold", "bronze", "rainbow", "multicolor"
    };

    private static Random random = new Random();

    public static String getRandomTask() {
        String adjective = adjectives[random.nextInt(adjectives.length)];
        String verb = verbs[random.nextInt(verbs.length)];

        return adjective + " " + verb;
    }

    public static String getRandomCategory() {
        return categories[random.nextInt(categories.length)];
    }

    public static String getRandomAttachmentType() {
        return attachmentTypes[random.nextInt(attachmentTypes.length)];
    }

    public static boolean getRandomBoolean(){
        return random.nextBoolean();
    }

    public static int getRandomInt(int min, int max){
        return random.nextInt(max - min) + min;
    }

    public static Date getRandomDateFromNow() {
        Random random = new Random();
        long currentTimeMillis = System.currentTimeMillis();
        int randomMinutes = random.nextInt(5) + 1; // generate a random number between 1 and 5

        long randomTimeMillis = TimeUnit.MINUTES.toMillis(randomMinutes);
        return new Date(currentTimeMillis + randomTimeMillis);
    }

    public static Date getRandomDateFromAfter(Date afterDate) {
        Random random = new Random();
        long afterDateTimeMillis = afterDate.getTime();
        int randomMinutes = random.nextInt(5) + 1; // generate a random number between 1 and 5

        long randomTimeMillis = TimeUnit.MINUTES.toMillis(randomMinutes);
        return new Date(afterDateTimeMillis + randomTimeMillis);
    }

    public static String getRandomAnimal() {
        return animals[random.nextInt(animals.length)];
    }

    public static String getRandomColor() {
        return colors[random.nextInt(colors.length)];
    }

    public static String getRandomAnimalColor() {
        return getRandomAnimal() + " " + getRandomColor();
    }
}
