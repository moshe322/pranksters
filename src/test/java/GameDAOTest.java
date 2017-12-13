import com.littlejohnny.commons.database.dataObjects.Game;
import com.littlejohnny.commons.database.jdbc.DAO.DAOImpls.GameDAOImpl;
import com.littlejohnny.commons.database.jdbc.DAO.GameDAO;
import com.littlejohnny.commons.database.jdbc.connection.TransactionManager;
import com.littlejohnny.commons.database.jdbc.connection.TransactionManagerImpl;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameDAOTest {

    private TransactionManager txManager = new TransactionManagerImpl();
    private GameDAO gameDAO = new GameDAOImpl();
    private List<Game> list;

    {
        Map<String, String> map1 = new HashMap<>();
        map1.put("ico1", "value1");
        Map<String, String> map2 = new HashMap<>();
        map2.put("ico2", "value2");
        Map<String, String> map3 = new HashMap<>();
        map3.put("ico3", "value3");
        list = new ArrayList<>();
        list.add(new Game(1,"Manchkin", "Manchkin description", "Manchkin full description", "Manchkin ico", map1, (float) 4.5));
        list.add(new Game(2,"Uno", "Uno description", "Uno full description", "Uno ico", map2, (float) 3.5));
        list.add(new Game(3,"Stronghold", "Stronghold description", "Stronghold full description", "Stronghold ico", map3, (float) 5.0));

    }

    @Ignore
    @Before
    public void setUp() throws Exception {
        txManager.doInTransaction(() -> {
            for(Game item: list) {
                gameDAO.create(item);
            }
            return true;
        });
    }

    @Ignore
    @After
    public void shutDown() throws Exception {
        txManager.doInTransaction(() -> {
            gameDAO.deleteById(1);
            gameDAO.deleteById(2);
            gameDAO.deleteById(3);
            return true;
        });
    }

    @Ignore
    @Test
    public void selectAllTest() throws Exception {
        List<Game> result = txManager.doInTransaction(() -> gameDAO.selectAll());
        for(Game item: result) {
            System.out.println(item);
        }
    }

    @Ignore
    @Test
    public void updateByIdTest() throws Exception {
        Map<String, String> map1 = new HashMap<>();
        map1.put("ico1", "value1");
        Game object = new Game(1,"Manchkin", "Manchkin new description", "Manchkin new full description", "Manchkin new ico", map1, (float) 4.5);
        txManager.doInTransaction(() -> {
            gameDAO.updateById(1, object);
            Assert.assertEquals(object, gameDAO.selectById(1));
            return true;
        });
    }
}
