import com.littlejohnny.commons.database.jdbc.utills.StringParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StringParserTest {
    @Test
    public void stringToMapTest1() {
        Map<String, String> map = StringParser.stringToMap(null);
        Assert.assertEquals(new HashMap<String, String>(), map);
    }

    @Test
    public void stringToMapTest2() {
        Map<String, String> map = StringParser.stringToMap("");
        Assert.assertEquals(new HashMap<String, String>(), map);
    }

    @Test
    public void stringToMapTest3() {
        Map<String, String> actual = StringParser.stringToMap("manchkin:manchkin.jpg-_-uno:uno.jpg-_-");
        Map<String, String> expected = new HashMap<>();
        expected.put("manchkin","manchkin.jpg");
        expected.put("uno","uno.jpg");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mapToStringTest1() {
        String actual = StringParser.mapToString(null);
        Assert.assertEquals("", actual);
    }

    @Test
    public void mapToStringTest2() {
        String actual = StringParser.mapToString(new HashMap<>());
        Assert.assertEquals("", actual);
    }

    @Test
    public void mapToStringTest3() {
        Map<String, String> input = new HashMap<>();
        input.put("manchkin","manchkin.jpg");
        input.put("uno","uno.jpg");
        String actual = StringParser.mapToString(input);
        Assert.assertEquals("manchkin:manchkin.jpg-_-uno:uno.jpg-_-", actual);
    }


}
