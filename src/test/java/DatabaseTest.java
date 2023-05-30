import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {
    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAddUser() {
        database.addUser(new User(1, "a1", "Robert"));
        assertEquals(1, database.getUsersCount());
    }
    @Test
    public void testPrintAllUsers() {
        User user1 = new User(1, "a1", "Robert");
        User user2 = new User(2, "a2", "Martin");

        database.addUser(user1);
        database.addUser(user2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String expectedOutput = "All users in the database:" + System.lineSeparator() +
                "User [userId=1, userGuid=a1, userName=Robert]" + System.lineSeparator() +
                "User [userId=2, userGuid=a2, userName=Martin]" + System.lineSeparator();

        database.printAllUsers();

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testDeleteAllUsers() {
        User user1 = new User(1, "a1", "Robert");
        User user2 = new User(2, "a2", "Martin");

        database.addUser(user1);
        database.addUser(user2);

        database.deleteAllUsers();

        assertEquals(0, database.getUsersCount());
    }
}
