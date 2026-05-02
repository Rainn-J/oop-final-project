public class Item {
    private String name;
    private String notes;
    private String location;
    private Category category;

    public Item(String name, String notes, String location, Category category) {
        this.name = name;
        this.notes = notes;
        this.location = location;
        this.category = category;
    }

    public String getName() { return name; }
    public String getNotes() { return notes; }
    public String getLocation() { return location; }
    public Category getCategory() { return category; }

    public void setName(String name) { this.name = name; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setLocation(String location) { this.location = location; }
    public void setCategory(Category category) { this.category = category; }
}