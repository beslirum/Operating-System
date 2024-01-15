import java.util.concurrent.Semaphore;

class River {
    Semaphore boatSem = new Semaphore(1);
    Semaphore wolfSem = new Semaphore(0);
    Semaphore sheepSem = new Semaphore(0);
    Semaphore cabbageSem = new Semaphore(0);

    int wolvesOnLeft = 0;
    int sheepOnLeft = 0;
    int cabbagesOnLeft = 0;

    // Other variables to track the number of wolves, sheep, and cabbages on each side

    public void crossRiver(String creature) {
        try {
            boatSem.acquire();

            // Ensure that it's safe to board the boat based on the current configuration
            if (creature.equals("wolf") && wolvesOnLeft == 0) {
                // It's not safe for the wolf to be alone on the left side
                boatSem.release();
                return;
            } else if (creature.equals("sheep") && sheepOnLeft == 0) {
                // It's not safe for the sheep to be alone on the left side
                boatSem.release();
                return;
            } else if (creature.equals("cabbage") && cabbagesOnLeft == 0) {
                // It's not safe for the cabbage to be alone on the left side
                boatSem.release();
                return;
            }

            // Board the boat
            if (creature.equals("wolf")) {
                wolvesOnLeft--;
            } else if (creature.equals("sheep")) {
                sheepOnLeft--;
            } else if (creature.equals("cabbage")) {
                cabbagesOnLeft--;
            }

            // Update variables to reflect the new state of the river

            boatSem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Other methods to check safety and update the state of the river
}

class Creature implements Runnable {
    private String type;
    private River river;

    public Creature(String type, River river) {
        this.type = type;
        this.river = river;
    }

    @Override
    public void run() {
        while (true) {
            // Perform actions based on the type of creature (wolf, sheep, cabbage)

            // Cross the river using the river.crossRiver() method
            river.crossRiver(type);

            // Sleep for a random amount of time before the next arrival
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class RiverCrossingProblem {
    public static void main(String[] args) {
        River river = new River();

        // Create and start threads for wolves, sheep, and cabbages
        Thread wolfThread = new Thread(new Creature("wolf", river));
        Thread sheepThread = new Thread(new Creature("sheep", river));
        Thread cabbageThread = new Thread(new Creature("cabbage", river));

        wolfThread.start();
        sheepThread.start();
        cabbageThread.start();
    }
}
