package Activities;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Activity1 {

    static List<String> list ;
    @BeforeAll
    static public void setUp(){
        list =  new ArrayList<>();
        list.add("alpha");
        list.add("beta");
    }

    @Order(1)
    @Test
    public void  insertTest(){
        assertEquals(2,list.size(),"size is 2");
        list.add("gamma");
        assertEquals(3,list.size()," size is 3");
        assertEquals("alpha",list.get(0));
        assertEquals("beta",list.get(1));
        assertEquals("gamma",list.get(2));
    }

    @Test
    public void  replaceTest(){
        assertEquals(2,list.size(),"size is 2");
        list.add("gamma");
        assertEquals(3,list.size()," size is 3");
        list.add(1,"omega");
        assertEquals("alpha",list.get(0));
        assertEquals("beta",list.get(1),"Wrong element");
        assertEquals("gamma",list.get(2));
    }
}
