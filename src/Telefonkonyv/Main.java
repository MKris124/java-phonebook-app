package Telefonkonyv;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactRepository contactRepository = new PersonRepo();
        AppController appController = new AppControllerImp(contactRepository);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Válassz egy műveletet: (1 - hozzáadás, 2 - törlés, 3 - listázás, 4 - keresés, 5 - módosítás, 0 - kilépés)");
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1 -> appController.addContact();
                    case 2 -> appController.removeContact();
                    case 3 -> appController.listContacts();
                    case 4 -> appController.searchContacts();
                    case 5 -> appController.updateContact();
                    case 0 -> {
                        System.out.println("Kilépés...");
                        System.exit(0);
                    }
                    default -> System.out.println("Érvénytelen választás!");
                }
            }catch (Exception e){
                e.getMessage();
            }
        }
    }
}