import don.chung.util.AppUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;

public class TestConnection {

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testConnection() {
        // Test connection
        Session session = AppUtils.getDriver().session(SessionConfig.forDatabase(AppUtils.DB_NAME));
        session.beginTransaction();
        session.close();
    }

}
