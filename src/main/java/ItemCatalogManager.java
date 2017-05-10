package src.main.java;

/**
 * Created by Jordan on 5/9/2017.
 */
public final class ItemCatalogManager {

    private static ItemCatalogManager instance;

    public static ItemCatalogManager getInstance() {
        if (instance == null) {
            instance = new ItemCatalogManager();
        }
        return instance;
    }

    private ItemCatalogManager() {}
}
