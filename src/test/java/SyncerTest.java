import net.kigawa.kutil.kutil.util.Syncer;

public class SyncerTest {
    private Syncer syncer = new Syncer(3);

    private SyncerTest() {
        new Thread(this::task).start();
        new Thread(this::task0).start();
        new Thread(this::task1).start();
    }

    public static void main(String[] args) {
        new SyncerTest();
    }

    private void task() {
        int order = 2;
        syncer.setTask(() -> System.out.println("task " + order), order);
    }

    private void task0() {
        int order = 1;
        syncer.setTask(() -> System.out.println("task " + order), order);

    }

    private void task1() {
        int order = 0;
        syncer.setTask(() -> System.out.println("task " + order), order);

    }
}
