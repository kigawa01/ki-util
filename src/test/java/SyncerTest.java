import net.kigawa.kutil.kutil.interfaces.LoggerInterface;
import net.kigawa.kutil.kutil.util.Syncer;

public class SyncerTest {
    private Syncer syncer;

    private SyncerTest(LoggerInterface logger) {
        syncer = new Syncer(logger, -1);
        new Thread(this::task).start();
        new Thread(this::task0).start();
        new Thread(this::task1).start();
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

    public static void main(String[] args) {
        new SyncerTest(new LoggerInterface() {
            @Override
            public void fine(Object... o) {

            }

            @Override
            public void warning(Object... o) {

            }

            @Override
            public void severe(Object... o) {

            }

            @Override
            public void info(Object... o) {

            }

            @Override
            public void all(Object... o) {

            }

            @Override
            public void finer(Object... o) {

            }

            @Override
            public void finest(Object... o) {

            }
        });
    }
}
