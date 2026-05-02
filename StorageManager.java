import java.util.*;

public class StorageManager {
    private List<StorageUnit> storageUnits = new ArrayList<>();

    public void addStorageUnit(StorageUnit unit) {
        storageUnits.add(unit);
    }

    public void deleteStorageUnit(StorageUnit unit) {
        storageUnits.remove(unit);
    }

    public List<StorageUnit> getStorageUnits() {
        return storageUnits;
    }
}