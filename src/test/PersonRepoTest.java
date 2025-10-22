package test;
import Telefonkonyv.ContactRepository;
import Telefonkonyv.Person;
import Telefonkonyv.PersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
//2. teszt, mert szintén nem csinál semmit a fájllal.
public class PersonRepoTest {

    private ContactRepository personRepo;

    @BeforeEach
    void setUp() {
        personRepo = new PersonRepo();
    }

    @Test
    void addContactTest() {
        int initialSize = personRepo.getAllContacts().size();
        Person newContact = new Person("John", "Doe", "Johnny", "123 Main St", "555-1234", "555-5678");
        personRepo.addContact(newContact);
        int newSize = personRepo.getAllContacts().size();
        Assertions.assertEquals(initialSize + 1, newSize);
        Assertions.assertTrue(personRepo.getAllContacts().contains(newContact));
    }

    @Test
    void removeContactTest() {
        int initialSize = personRepo.getAllContacts().size();
        Person contactToRemove = personRepo.getAllContacts().get(0);
        personRepo.removeContact(contactToRemove);
        int newSize = personRepo.getAllContacts().size();
        Assertions.assertEquals(initialSize - 1, newSize);
        Assertions.assertFalse(personRepo.getAllContacts().contains(contactToRemove));
    }

    @Test
    void getAllContactsTest() {
        List<Person> allContacts = personRepo.getAllContacts();
        Assertions.assertNotNull(allContacts);
        Assertions.assertTrue(allContacts.size() > 0);
    }

    @Test
    void searchContactsByNameTest() {
        List<Person> results = personRepo.searchContactsByName("John");
        Assertions.assertNotNull(results);
        Assertions.assertTrue(results.size() > 0);
    }


    @Test
    public void testUpdateLastName() {
        String input = "keresztnév\nJohn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Person person = new Person("Doe", "Jane", "jane", "123 Main St", "123-456-7890", "987-654-3210");
        new PersonRepo().updateContact(person);

        Assertions.assertEquals("John", person.getLastName());
    }

    @Test
    public void testUpdateNickname() {
        String input = "becenév\nj\n";
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Person person = new Person("Doe", "Jane", "jane", "123 Main St", "123-456-7890", "987-654-3210");
        new PersonRepo().updateContact(person);

        Assertions.assertEquals("j", person.getNickname());
    }

    @Test
    public void testUpdateInvalidField() {
        String input = "invalid\n";
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Person person = new Person("Doe", "Jane", "jane", "123 Main St", "123-456-7890", "987-654-3210");
        new PersonRepo().updateContact(person);

        Assertions.assertNotEquals("invalid", person.getFirstName());
        Assertions.assertNotEquals("invalid", person.getLastName());
        Assertions.assertNotEquals("invalid", person.getNickname());
        Assertions.assertNotEquals("invalid", person.getAddress());
        Assertions.assertNotEquals("invalid", person.getWorkPhoneNumber());
        Assertions.assertNotEquals("invalid", person.getPrivatePhoneNumber());
    }
}