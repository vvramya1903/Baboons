package baboon_synchronize;

public class baboonSynchronize {
    private static int direction0Count = 0;
    private static int direction1Count = 0;

    public static void main(String[] args) {
        // parse command line argument to determine the number of baboons
        int numBaboons = Integer.parseInt(args[0]);

        for (int i = 0; i < numBaboons; i++) {
            int direction = (int) (Math.random() * 2); // randomly generate a direction for each baboon

            Thread t = new Thread(new Baboon(i, direction));
            t.start();
        }
    }

    static class Baboon implements Runnable {
        private int direction;
        private int val;

        public Baboon(int val, int direction) {
            this.direction = direction;
            this.val = val + 1;
        }

        public void run() {
            try {
                if (direction == 0) {
                    synchronized (this) {
                        System.out.println("baboon " + val + " has came from left direction");
                        System.out.println("baboon " + val + " is waiting at left to get the rope");
                        direction0Count++;
                        if (direction0Count == 1) {
                            System.out.println("baboon " + val + " started to cross from left direction");
                        }
                    }
                    Thread.sleep(1000); // simulate time taken to cross the rope
                    synchronized (this) {
                        direction0Count--;
                        if (direction0Count == 0) {
                            System.out.println("baboon " + val + " crossed the rope from left direction");
                        }
                    }
                } else {
                    synchronized (this) {
                        System.out.println("baboon " + val + " has came from right direction");
                        System.out.println("baboon " + val + " is waiting at right to get the rope");
                        direction1Count++;
                        if (direction1Count == 1) {
                            System.out.println("baboon " + val + " started to cross from right direction");
                        }
                    }
                    Thread.sleep(1000); // simulate time taken to cross the rope
                    synchronized (this) {
                        direction1Count--;
                        if (direction1Count == 0) {
                            System.out.println("baboon " + val + " crossed the rope from right direction");
                        }
                    }
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
