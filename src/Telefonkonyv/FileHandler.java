package Telefonkonyv;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILENAME = "contacts.txt";

    public static void saveToFile(List<Person> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILENAME), StandardCharsets.UTF_8))) {
            for (Person person : contacts) {
                writer.write(person.getFirstName() + "," + person.getLastName() + ","
                        + person.getNickname() + "," + person.getAddress() + ","
                        + person.getWorkPhoneNumber() + "," + person.getPrivatePhoneNumber());
                writer.newLine();
            }
            System.out.println("A kontaktok sikeresen mentésre kerültek a " + FILENAME + " fájlba.");
        } catch (IOException e) {
            System.err.println("Hiba történt a " + FILENAME + " fájlba történő mentés során: " + e.getMessage());
        }
    }

    public static List<Person> loadFromFile() {
        List<Person> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(FILENAME), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String firstName = fields[0];
                String lastName = fields[1];
                String nickname = fields[2];
                String address = fields[3];
                String workNumber = fields[4];
                String privateNumber = fields[5];
                contacts.add(new Person(firstName, lastName, nickname, address, workNumber, privateNumber));
            }
            System.out.println("A kontaktok sikeresen betöltődtek a " + FILENAME + " fájlból.");
        } catch (IOException e) {
            System.err.println("Hiba történt a " + FILENAME + " fájl olvasása során: " + e.getMessage());
        }
        return contacts;
    }
}