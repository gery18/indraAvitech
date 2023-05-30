import java.util.LinkedList;
import java.util.Queue;

public class IndraAvitech {
    public static void main(String[] args) {
        Queue<String> commandQueue = new LinkedList<>();
        Database database = new Database();

        CommandProcessor commandProcessor = new CommandProcessor(commandQueue, database);
        Thread processorThread = new Thread(commandProcessor);
        processorThread.start();

        // Add commands to the queue
        commandQueue.add("Add 1 a1 Robert");
        commandQueue.add("Add 2 a2 Martin");
        commandQueue.add("PrintAll");
        commandQueue.add("DeleteAll");
        commandQueue.add("PrintAll");

        // Notify the processor thread that commands are available
        synchronized (commandQueue) {
            commandQueue.notifyAll();
        }
    }
}
