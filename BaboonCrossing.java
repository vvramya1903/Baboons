import java.util.concurrent.Semaphore;

public class BaboonCrossing{
    
    static Semaphore rope = new Semaphore(1);
    static int count = 0;
    static int MAX_CROSSING_TIME = 5000; // Maximum time for baboon crossing in ms
    
    public static void main(String[] args) {
        int numBaboons = Integer.parseInt(args[0]);
        for (int i = 0; i < numBaboons; i++) {
            new Thread(new Baboon(i)).start();
        }
    }
    
    static class Baboon implements Runnable {
        
        int id;
        String side = "left";
        
        public Baboon(int id) {
            this.id = id;
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep((int)(Math.random() * MAX_CROSSING_TIME)); // Random crossing time
                    System.out.println("Baboon " + id + " is on the " + side + " side of the canyon and wants to cross.");
                    if (side.equals("left")) {
                        rope.acquire();
                        count++;
                        System.out.println("Baboon " + id + " is crossing the rope from the left side to the right side.");
                        Thread.sleep((int)(Math.random() * MAX_CROSSING_TIME));
                        System.out.println("Baboon " + id + " has crossed the rope from the left side to the right side.");
                        count--;
                        if (count == 0) {
                            rope.release();
                            System.out.println("************************");
                            System.out.println("The rope is now empty.");
                            System.out.println("************************");
                            side = "right";
                        }
                    } else {
                        rope.acquire();
                        count--;
                        System.out.println("Baboon " + id + " is crossing the rope from the right side to the left side.");
                        Thread.sleep((int)(Math.random() * MAX_CROSSING_TIME));
                        System.out.println("Baboon " + id + " has crossed the rope from the right side to the left side.");
                        count++;
                        if (count == 0) {
                            rope.release();
                            System.out.println("************************");
                            System.out.println("The rope is now empty.");
                            System.out.println("************************");
                            side = "left";
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
