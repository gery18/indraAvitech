import java.util.Queue;

public class CommandProcessor implements Runnable {
    private Queue<String> commandQueue;
    private Database database;
    private boolean active = true;
    public CommandProcessor(Queue<String> commandQueue, Database database) {
        this.commandQueue = commandQueue;
        this.database = database;
    }

    @Override
    public void run() {
        while (active) {
            synchronized (commandQueue) {
                while (commandQueue.isEmpty()) {
                    try {
                        commandQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                String command = commandQueue.poll();
                if (command != null) {
                    processCommand(command);
                }
            }
        }
    }

    private void processCommand(String command) {
        String[] parts = command.split(" ");
        String commandType = parts[0];

        if (commandType.equals("Add")) {
            int userId = Integer.parseInt(parts[1]);
            String userGuid = parts[2];
            String userName = parts[3];
            User user = new User(userId, userGuid, userName);
            database.addUser(user);
        } else if (commandType.equals("PrintAll")) {
            database.printAllUsers();
        } else if (commandType.equals("DeleteAll")) {
            database.deleteAllUsers();
        } else if (commandType.equals("Quit")) {
            active = false;
        } else {
            System.out.println("Unknown command: " + commandType);
        }
    }
}