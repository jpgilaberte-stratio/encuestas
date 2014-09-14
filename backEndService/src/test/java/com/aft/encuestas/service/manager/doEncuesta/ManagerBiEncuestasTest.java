package com.aft.encuestas.service.manager.doEncuesta;

import com.aft.encuestas.dao.manager.EncuestasDaoImpl;
import com.aft.encuestas.model.Encuesta;
import junit.framework.TestCase;
import org.mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ManagerBiEncuestasTest extends TestCase {

    private ManagerBiEncuestas mbe;

    public void setUp() throws Exception {
        mbe = new ManagerBiEncuestas();
        mbe.setDaoImpl(Mockito.mock(EncuestasDaoImpl.class));
        super.setUp();
    }


    public void testFind() throws Exception {

       Encuesta encuesta = null;
       List mockList = new ArrayList();
       mockList.add(new Encuesta());

       Mockito.when(mbe.getDaoImpl().findEncuesta(Mockito.anyString(), Mockito.anyString())).thenReturn(mockList);
       encuesta =  mbe.find(new Encuesta());

       assertNotNull(encuesta);

    }

    public void testAdd() throws Exception {
        List mockList = new ArrayList();
        mockList.add(new Encuesta());
        Mockito.when(mbe.getDaoImpl().add(Mockito.anyMap())).thenReturn(new Integer(1));
        boolean b = mbe.add(new HashMap());
        assertEquals(true, b);
    }

    public void testDel() throws Exception {
        List mockList = new ArrayList();
        mockList.add(new Encuesta());
        Mockito.when(mbe.getDaoImpl().delete(Mockito.anyString())).thenReturn(new Integer(1));
        boolean b = mbe.del("2");
        assertEquals(true, b);
    }

}