package test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import Telefonkonyv.Person;
import Telefonkonyv.AppControllerImp;
import Telefonkonyv.ContactRepository;
import Telefonkonyv.PersonRepo;
import org.junit.Before;
import org.junit.Test;
//3. teszt, mert ez már csinál dolgokat is
public class AppControllerImpTest {
    private ContactRepository contactRepository;
    private AppControllerImp appController;

    @Before
    public void setUp() {
        contactRepository = new PersonRepo();
        appController = new AppControllerImp(contactRepository);
    }

    @Test
    public void testAddContact() {
        String input = "John\nDoe\nJD\n123 Main St\n555-1234\n555-5678\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        appController.addContact();
        List<Person> contacts = contactRepository.getAllContacts();

        assertEquals(5, contacts.size());
        Person contact = contacts.get(0);
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("Johnny", contact.getNickname());
        assertEquals("123 Main St", contact.getAddress());
        assertEquals("555-1234", contact.getWorkPhoneNumber());
        assertEquals("555-5678", contact.getPrivatePhoneNumber());
    }

    @Test
    public void testRemoveContact() {
        Person contact1 = new Person("Joh", "Doe", "JD", "123 Main St", "555-1234", "555-5678");
        Person contact2 = new Person("Jane", "Doe", "Janie", "456 Elm St", "555-4321", "555-8765");

        contactRepository.addContact(contact1);
        contactRepository.addContact(contact2);

        String input = "Joh Doe\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        appController.removeContact();

        List<Person> contacts = contactRepository.getAllContacts();

        assertEquals(2, contacts.size());
        System.out.println(contacts.contains(contact2));
        assertFalse(contacts.contains(contact1));
        assertTrue(contacts.contains(contact2));


    }

    @Test
    public void testSearchContacts() {
        Person contact1 = new Person("John", "Doe", "JD", "123 Main St", "555-1234", "555-5678");
        Person contact2 = new Person("Jane", "Doe", "Janie", "456 Elm St", "555-4321", "555-8765");

        contactRepository.addContact(contact1);
        contactRepository.addContact(contact2);

        String input = "Doe\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        appController.searchContacts();
    }
}
