package lv.training.inventory.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataBaseFactoryTest {

    @Test
    void createDatabase() {
        Database db = DataBaseFactory.createDatabase();
        assertNotNull(db);
        assertTrue(db instanceof InMemoryDb);
    }
}