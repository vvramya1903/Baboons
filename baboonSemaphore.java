package baboon_semaphore;

import java.util.concurrent.Semaphore;

public class baboonSemaphore {
    private static Semaphore direction0 = new Semaphore(1); // semaphore for direction 0
    private static Semaphore direction1 = new Semaphore(1); // semaphore for direction 1
    private static int baboonCount = 0;

    public static void main(String[] args) {
        // parse command line argument to determine the number of baboons
        int numBaboons = Integer.parseInt(args[0]);

        for (int i = 0; i < numBaboons; i++) {
            int direction = (int) (Math.random() * 2); // randomly generate a direction for each baboon

            Thread t = new Thread(new Baboon(i,direction));
            t.start();
        }
    }

    static class Baboon implements Runnable {
        private int direction;
        public int val;

        public Baboon(int val,int direction) {
            this.direction = direction;
            this.val = val+1;
        }

        public void run() {
            try {
                if (direction == 0) {
                    direction0.acquire(); // acquire the semaphore for direction 0
                    System.out.println("baboon " + val + " has came from left direction");
                    System.out.println("baboon " + val + " is waiting at left to get the rope");
                    baboonCount++;
                    if (baboonCount == 1) {
                        System.out.println("baboon " + val + " started to cross from left direction");
                    }
                    direction0.release(); // release the semaphore for direction 0
                    Thread.sleep(1000); // simulate time taken to cross the rope
                    direction0.acquire(); // acquire the semaphore for direction 0
                    baboonCount--;
                    if (baboonCount == 0) {
                        System.out.println("baboon " + val + " crossed the rope from left direction");
                    }
                    direction0.release(); // release the semaphore for direction 0
                } else {
                    direction1.acquire(); // acquire the semaphore for direction 1
                    System.out.println("baboon " + val + " has came from right direction");
                    System.out.println("baboon " + val + " is waiting at right to get the rope");
                    baboonCount++;
                    if (baboonCount == 1) {
                        System.out.println("baboon " + val + " started to cross from right direction");
                    }
                    direction1.release(); // release the semaphore for direction 1
                    Thread.sleep(1000); // simulate time taken to cross the rope
                    direction1.acquire(); // acquire the semaphore for direction 1
                    baboonCount--;
                    if (baboonCount == 0) {
                        System.out.println("baboon " + val + " crossed the rope from right direction");
                    }
                    direction1.release(); // release the semaphore for direction 1
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String toString() {
            return "Baboon " + hashCode();
        }
    }
}
