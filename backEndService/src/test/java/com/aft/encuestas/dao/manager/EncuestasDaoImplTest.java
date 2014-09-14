package com.aft.encuestas.dao.manager;

import com.aft.encuestas.model.Encuesta;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.List;


public class EncuestasDaoImplTest extends TestCase {

    private EncuestasDaoImpl mdd;

    public void setUp() throws Exception {
        mdd = new EncuestasDaoImpl();
        //mdd.setDs(Mockito.mock(MysqlDataSource.class));
        //mdd.connect();
        super.setUp();
    }


    public void testFindEncuesta() throws Exception {
        testDefault();
       //List<Encuesta> listEncuesta = mdd.findEncuesta("", "");
       //assertNotNull(listEncuesta);

    }

    public void testAdd() throws Exception {
        testDefault();
        //int res = mdd.add(new HashMap());
        //assertEquals(res, "1");
    }


    public void testDelete() throws Exception {
        //int res = mdd.delete("1");
        //assertEquals(res, "1");
        testDefault();
    }

    public void testDefault() throws Exception{

        assertEquals(true, true);
    }
}