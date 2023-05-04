import java.util.concurrent.Semaphore;

public class Rope {
    private Semaphore leftSemaphore;
    private Semaphore rightSemaphore;

    public Rope() {
        leftSemaphore = new Semaphore(1);
        rightSemaphore = new Semaphore(1);
    }

    public Semaphore getLeftSemaphore() {
        return leftSemaphore;
    }

    public Semaphore getRightSemaphore() {
        return rightSemaphore;
    }
}
