import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StorageManager manager = new StorageManager();

    public static void main(String[] args) {
        seedData();
        showHomePage();
    }

    private static void seedData() {
        StorageUnit lockerA = new StorageUnit("Locker A", "Main hallway locker");
        lockerA.addItem(new Item("Cap", "Blue baseball cap", "Top shelf", Category.CLOTHING));
        lockerA.addItem(new Item("Notebook", "Math notes", "Middle shelf", Category.DOCUMENTS));
        manager.addStorageUnit(lockerA);
    }

    private static void showHomePage() {
        while (true) {
            System.out.println("\nWelcome!");
            System.out.println("-------------------------");
            System.out.println("Storage units:");

            List<StorageUnit> units = manager.getStorageUnits();

            if (units.isEmpty()) {
                System.out.println("No storage units available.");
            } else {
                for (int i = 0; i < units.size(); i++) {
                    System.out.println((i + 1) + ". " + units.get(i).getName());
                }
            }

            System.out.println("\nPlease type following numbers:");
            System.out.println("View storage unit (1)");
            System.out.println("Add storage unit  (2)");
            System.out.println("Search item       (3)");
            System.out.println("Exit              (0)");

            int choice = readInt("Choice: ");

            switch (choice) {
                case 1 -> selectStorageUnit();
                case 2 -> addStorageUnit();
                case 3 -> searchItem();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void selectStorageUnit() {
        List<StorageUnit> units = manager.getStorageUnits();

        if (units.isEmpty()) {
            System.out.println("No storage units.");
            return;
        }

        System.out.println("\nSelect storage unit:");
        for (int i = 0; i < units.size(); i++) {
            System.out.println((i + 1) + ". " + units.get(i).getName());
        }
        System.out.println("Back (0)");

        int choice = readInt("Choice: ");
        if (choice == 0) return;

        if (choice < 1 || choice > units.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        showStorageUnitPage(units.get(choice - 1));
    }

    private static void showStorageUnitPage(StorageUnit unit) {
        while (true) {
            System.out.println("\n" + unit.getName());
            System.out.println("Notes: " + unit.getNotes());
            System.out.println("-------------------------");

            List<Item> items = unit.getItems();
            if (items.isEmpty()) {
                System.out.println("No items.");
            } else {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i).getName());
                }
            }

            System.out.println("-------------------------");
            System.out.println("Edit storage unit (1)");
            System.out.println("Delete storage unit (2)");
            System.out.println("View item (3)");
            System.out.println("Add item (4)");
            System.out.println("Categorize item (5)");
            System.out.println("Back (0)");

            int choice = readInt("Choice: ");

            switch (choice) {
                case 1 -> editStorageUnit(unit);
                case 2 -> {
                    manager.deleteStorageUnit(unit);
                    return;
                }
                case 3 -> selectItem(unit);
                case 4 -> addItem(unit);
                case 5 -> showItemsByCategory(unit);
                case 0 -> { return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private static void addStorageUnit() {
        String name = readLine("Name: ");
        String notes = readLine("Notes: ");
        manager.addStorageUnit(new StorageUnit(name, notes));
    }

    private static void editStorageUnit(StorageUnit unit) {
        unit.setName(readLine("New name: "));
        unit.setNotes(readLine("New notes: "));
    }

    private static void addItem(StorageUnit unit) {
        String name = readLine("Name: ");
        String notes = readLine("Notes: ");
        String location = readLine("Location: ");
        Category category = chooseCategory();

        unit.addItem(new Item(name, notes, location, category));
    }

    private static void selectItem(StorageUnit unit) {
        List<Item> items = unit.getItems();
        if (items.isEmpty()) return;

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }
        System.out.println("Back (0)");

        int choice = readInt("Choice: ");
        if (choice == 0) return;

        showItemPage(unit, items.get(choice - 1));
    }

    private static void showItemPage(StorageUnit unit, Item item) {
        while (true) {
            System.out.println("\n" + item.getName());
            System.out.println("-------------------------");
            System.out.println("Location: " + item.getLocation());
            System.out.println("Notes: " + item.getNotes());
            System.out.println("Category: " + item.getCategory());
            System.out.println("-------------------------");
            System.out.println("Delete (1)");
            System.out.println("Edit (2)");
            System.out.println("Change location (3)");
            System.out.println("Back (0)");

            int choice = readInt("Choice: ");

            switch (choice) {
                case 1 -> { unit.removeItem(item); return; }
                case 2 -> editItem(item);
                case 3 -> item.setLocation(readLine("New location: "));
                case 0 -> { return; }
            }
        }
    }

    private static void editItem(Item item) {
        item.setName(readLine("New name: "));
        item.setNotes(readLine("New notes: "));
        item.setCategory(chooseCategory());
    }

    private static void showItemsByCategory(StorageUnit unit) {
        for (Category c : Category.values()) {
            System.out.println("\n" + c + ":");
            for (Item i : unit.getItems()) {
                if (i.getCategory() == c) {
                    System.out.println("- " + i.getName());
                }
            }
        }
        readLine("Enter to go back...");
    }

    private static void searchItem() {
        String keyword = readLine("Search: ").toLowerCase();

        for (StorageUnit unit : manager.getStorageUnits()) {
            for (Item item : unit.getItems()) {
                if (item.getName().toLowerCase().contains(keyword)) {
                    System.out.println(item.getName() + " | " + unit.getName());
                }
            }
        }
        readLine("Enter to go back...");
    }

    private static Category chooseCategory() {
        Category[] values = Category.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }
        return values[readInt("Choice: ") - 1];
    }

    private static String readLine(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
    }
}