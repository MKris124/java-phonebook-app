package Telefonkonyv;
import java.util.List;
import java.util.Scanner;

public class AppControllerImp implements AppController {
    private ContactRepository contactRepository;

    public AppControllerImp(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void addContact() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adja meg a vezetéknevet: ");
        String firstName = scanner.nextLine();
        while (firstName.trim().isEmpty()) {
            System.out.println("Vezetéknév kötelező! Adja meg újra: ");
            firstName = scanner.nextLine();
        }

        System.out.println("Adja meg a keresztnevet: ");
        String lastName = scanner.nextLine();
        while (lastName.trim().isEmpty()) {
            System.out.println("Keresztnév kötelező! Adja meg újra: ");
            lastName = scanner.nextLine();
        }

        System.out.println("Adja meg a becenevet: ");
        String nickname = scanner.nextLine();
        while (nickname.trim().isEmpty()) {
            System.out.println("Becenév kötelező! Adja meg újra: ");
            nickname = scanner.nextLine();
        }

        System.out.println("Adja meg a címet: ");
        String address = scanner.nextLine();
        while (address.trim().isEmpty()) {
            System.out.println("Cím kötelező! Adja meg újra: ");
            address = scanner.nextLine();
        }

        System.out.println("Adja meg a munkahelyi telefonszámot (ha nincs, nyomja meg az Enter-t): ");
        String workPhoneNumber = scanner.nextLine();
        if (workPhoneNumber.trim().isEmpty()) {
            workPhoneNumber = null;
        }

        System.out.println("Adja meg a privát telefonszámot: ");
        String privatePhoneNumber = scanner.nextLine();
        while (privatePhoneNumber.trim().isEmpty()) {
            System.out.println("Privát telefonszám kötelező! Adja meg újra: ");
            privatePhoneNumber = scanner.nextLine();
        }

        Person contact = new Person(firstName, lastName, nickname, address, workPhoneNumber, privatePhoneNumber);
        contactRepository.addContact(contact);
    }

    @Override
    public void removeContact() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adja meg a törlendő kontakt nevét ('vezetéknév+keresztnév' vagy 'keresztnév' vagy 'vezeteknév'): ");
        String name = scanner.nextLine();

        List<Person> searchResults = contactRepository.searchContactsByName(name);
        if (searchResults.isEmpty()) {
            System.out.println("Nem találtam ilyen nevű kontaktot!");
            return;
        }

        if (searchResults.size() == 1) {
            Person contact = searchResults.get(0);
            contactRepository.removeContact(contact);
            System.out.println("A " + contact.getFirstName() + " " + contact.getLastName() + " kontakt sikeresen törölve!");
        } else {
            System.out.println("Több találat van, kérlek pontosítsd a keresést!");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println((i + 1) + ". " + searchResults.get(i).toString());
            }
            System.out.println("Kérlek válassz egy számot a felsorolt találatok közül: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index >= 1 && index <= searchResults.size()) {
                Person contact = searchResults.get(index - 1);
                contactRepository.removeContact(contact);
                System.out.println("A " + contact.getFirstName() + " " + contact.getLastName() + " kontakt sikeresen törölve!");
            } else{
                System.out.println("Érvénytelen szám!");
            }
        }
    }
    @Override
    public double listContacts() {
        List<Person> contacts = contactRepository.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("Nincs egyetlen kontakt sem!");
        } else {
            for (Person contact : contacts) {
                System.out.println(contact.toString());
            }
        }
        return 0;
    }

    @Override
    public double searchContacts() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adja meg a keresett kontakt nevét ('vezetéknév+keresztnév' vagy 'keresztnév' vagy 'vezeteknév'): ");
        String name = scanner.nextLine();

        List<Person> searchResults = contactRepository.searchContactsByName(name);
        if (searchResults.isEmpty()) {
            System.out.println("Nem találtam ilyen nevű kontaktot!");
        } else {
            System.out.println("A találatok: ");
            for (Person contact : searchResults) {
                System.out.println(contact.toString());
            }
        }
        return 0;
    }

    @Override
    public void updateContact() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adja meg a módosítandó kontakt nevét ('vezetéknév+keresztnév' vagy 'keresztnév' vagy 'vezeteknév'): ");
        String name = scanner.nextLine();

        List<Person> searchResults = contactRepository.searchContactsByName(name);
        if (searchResults.isEmpty()) {
            System.out.println("Nem találtam ilyen nevű kontaktot!");
            return;
        }

        if (searchResults.size() == 1) {
            Person contact = searchResults.get(0);
            contactRepository.updateContact(contact);
            System.out.println("A " + contact.getFirstName() + " " + contact.getLastName() + " kontakt sikeresen módosítva!");
        } else {
            System.out.println("Több találat van, kérlek pontosítsd a keresést!");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println((i + 1) + ". " + searchResults.get(i).toString());
            }
            System.out.println("Kérlek válassz egy számot a felsorolt találatok közül: ");
            int index = scanner.nextInt();

            scanner.nextLine();
            if (index >= 1 && index <= searchResults.size()) {
                Person contact = searchResults.get(index - 1);
                contactRepository.updateContact(contact);
                System.out.println("A " + contact.getFirstName() + " " + contact.getLastName() + " kontakt sikeresen módosítva!");
            } else {
                System.out.println("Érvénytelen szám!");
            }
        }
    }
}

