package Telefonkonyv;
import java.util.List;

public interface ContactRepository {
    void addContact(Person contact);
    void removeContact(Person contact);
    List<Person> getAllContacts();
    List<Person> searchContactsByName(String name);
    void updateContact(Person contact);
}