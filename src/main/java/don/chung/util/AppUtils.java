package don.chung.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AppUtils {

    public static final String DB_NAME = "neo4j";

    /**
     * Convert a Node to a POJO
     *
     * @param node  the node to convert
     * @param clazz the class of the POJO
     * @param <T    extends Object> the type of the POJO
     * @return the POJO
     */
    public static <T> T convert(Node node, Class<T> clazz) {
        Map<String, Object> map = node.asMap();
        Gson gson = new Gson();
        String json = gson.toJson(map);
        return gson.fromJson(json, clazz);
    }

    public static <T> Map<String, Object> getProperties(T t) {
        Gson gson = new Gson();
        String json = gson.toJson(t);

        return gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        });
    }

    public static Driver getDriver() {
        String username = "neo4j";
        String password = "01082003";
        String uri = "neo4j://localhost:7687";
        return GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    public static LocalDate toLocalDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dtf);
    }

    public static LocalDate toLocalDate(OffsetDateTime date) {
        return date.toLocalDate();
    }

    public static OffsetDateTime toOffsetDateTime(LocalDate date) {
        return date.atTime(LocalTime.MIDNIGHT).atZone(ZoneId.of("UTC")).toOffsetDateTime();
    }

}
