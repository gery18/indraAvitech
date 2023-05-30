import java.util.LinkedList;

public class Database {
    private LinkedList<User> users;

    public Database() {
        this.users = new LinkedList<>();
    }

    public synchronized void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user);
    }

    public synchronized void printAllUsers() {
        System.out.println("All users in the database:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public synchronized void deleteAllUsers() {
        users.clear();
        System.out.println("All users deleted from the database.");
    }

    public int getUsersCount() {
        return users.size();
    }
}
