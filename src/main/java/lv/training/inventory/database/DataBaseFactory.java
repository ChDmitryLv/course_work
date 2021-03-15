package lv.training.inventory.database;

public class DataBaseFactory {
    public static Database createDatabase(){
        return new InMemoryDb();
    }
}
