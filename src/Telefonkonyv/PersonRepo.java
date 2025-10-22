package Telefonkonyv;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonRepo implements ContactRepository {
    private List<Person> contacts;

    public PersonRepo() {
        contacts = FileHandler.loadFromFile();
    }

    @Override
    public void addContact(Person contact) {
        contacts.add(contact);
        FileHandler.saveToFile(contacts);
    }

    @Override
    public void removeContact(Person contact) {
        contacts.remove(contact);
        FileHandler.saveToFile(contacts);
    }

    @Override
    public List<Person> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    @Override
    public List<Person> searchContactsByName(String name) {
        List<Person> results = new ArrayList<>();
        for (Person contact : contacts) {
            String Cname= contact.getFirstName()+" "+contact.getLastName();
            if (Cname.equalsIgnoreCase(name)|| contact.getFirstName().equalsIgnoreCase(name)||contact.getLastName().equalsIgnoreCase(name)) {
                results.add(contact);
            }
        }
        return results;
    }

    @Override
    public void updateContact(Person contact) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adja meg a módosítandó adatot (vezetéknév, keresztnév, becenév, cím, munkahelyi szám, privát szám): ");
        String field = scanner.nextLine();

        switch (field.toLowerCase()) {
            case "vezetéknév" -> {
                System.out.println("Adja meg az új vezetéknevet: ");
                String firstName = scanner.nextLine();
                contact.setFirstName(firstName);
            }
            case "keresztnév" -> {
                System.out.println("Adja meg az új keresztnevet: ");
                String lastName = scanner.nextLine();
                contact.setLastName(lastName);
            }
            case "becenév" -> {
                System.out.println("Adja meg az új becenevet: ");
                String nickname = scanner.nextLine();
                contact.setNickname(nickname);
            }
            case "cím" -> {
                System.out.println("Adja meg az új címet: ");
                String address = scanner.nextLine();
                contact.setAddress(address);
            }
            case "munkahelyi szám" -> {
                System.out.println("Adja meg az új munkahelyi telefonszámot: ");
                String workPhoneNumber = scanner.nextLine();
                contact.setWorkPhoneNumber(workPhoneNumber);
            }
            case "privát szám" -> {
                System.out.println("Adja meg az új privát telefonszámot: ");
                String privatePhoneNumber = scanner.nextLine();
                contact.setPrivatePhoneNumber(privatePhoneNumber);
            }
            default -> System.out.println("Érvénytelen adat.");
        }

        FileHandler.saveToFile(contacts);
    }

}
