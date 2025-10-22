package Telefonkonyv;

public interface AppController {
    void addContact();
    void removeContact();
    double listContacts();
    double searchContacts();
    void updateContact() throws Exception;
}
