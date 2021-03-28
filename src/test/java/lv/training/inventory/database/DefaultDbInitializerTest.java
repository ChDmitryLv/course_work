package lv.training.inventory.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultDbInitializerTest {

    @Test
    void initDb() {
        Database db = DataBaseFactory.createDatabase();
        DefaultDbInitializer defaultDbInitializer = new DefaultDbInitializer();
        assertEquals(0,db.readAll().size());
        defaultDbInitializer.initDb(db);
        assertEquals(2,db.readAll().size());
    }
}