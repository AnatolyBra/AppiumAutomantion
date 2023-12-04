import org.junit.Test;

import static org.junit.Assert.*;

public class MainClassTest {
    private final MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        int actual = mainClass.getLocalNumber();
        int expected = 14;
        assertEquals("Значение не равно заданному", expected, actual);
    }

    @Test
    public void testGetClassNumber() {
        boolean actual = mainClass.getClassNumber() > 45;
        assertTrue("Метод возращается значение меньше 45", actual);
    }

    @Test
    public void getClassString() {
        String text = mainClass.getClassString();
        boolean actual = text.contains("Hello") || text.contains("hello");
        assertTrue("Метод не возвращает 'Hello' или 'hello'", actual);
    }
}