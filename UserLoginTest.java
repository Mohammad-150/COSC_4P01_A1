import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserLoginTest {

    private UserLogin userLogin;

    @BeforeEach
    public void setUp() {
        userLogin = new UserLogin();
        userLogin.createUser("tester", "pass123");
    }

    @Test
    public void testSuccessfulLogin() {
        String result = userLogin.login("tester", "pass123");
        assertEquals("Login successful! Redirecting to dashboard.", result);
    }

    @Test
    public void testIncorrectCred() {
        String result = userLogin.login("tester", "wrong");
        assertEquals("Invalid username or password.", result);
    }

    @Test
    public void testEdgeCases() {
        String emptyUs = userLogin.login("", "pass123");
        String emptyPass = userLogin.login("tester", "");
        assertEquals("Invalid username or password.", emptyUs);
        assertEquals("Invalid username or password.", emptyPass);
    }

    @Test
    public void testLock() {
        userLogin.login("tester", "wrong");
        userLogin.login("tester", "wrong");
        userLogin.login("tester", "wrong");
        String result = userLogin.login("tester", "pass123");
        assertEquals("Account locked due to too many failed login attempts.", result);
    }
}
