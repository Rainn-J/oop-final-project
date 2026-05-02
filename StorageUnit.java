import java.util.*;

public class StorageUnit {
    private String name;
    private String notes;
    private List<Item> items = new ArrayList<>();

    public StorageUnit(String name, String notes) {
        this.name = name;
        this.notes = notes;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public String getName() { return name; }
    public String getNotes() { return notes; }

    public void setName(String name) { this.name = name; }
    public void setNotes(String notes) { this.notes = notes; }
}
