package test;
import Telefonkonyv.FileHandler;
import Telefonkonyv.Person;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
//1. teszt, mert nem csinál kb semmit
public class FileHandlerTest {
    @Test
    void testLoadFromFile() {
        FileHandler fileHandler = new FileHandler();
        List<Person> contacts = fileHandler.loadFromFile();
        assertEquals(1, contacts.size());
        assertEquals("John", contacts.get(0).getFirstName()); // a betöltött adatoknak meg kell egyezniük az előre definiált adatokkal
        assertEquals("Doe", contacts.get(0).getLastName());
        assertEquals("Johnny", contacts.get(0).getNickname());
        assertEquals("123 Main St", contacts.get(0).getAddress());
        assertEquals("555-1234", contacts.get(0).getWorkPhoneNumber());
        assertEquals("555-5678", contacts.get(0).getPrivatePhoneNumber());
    }
}
