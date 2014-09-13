package com.aft.encuestas.util;



public class FactoryManager {

	public static final String CLASS_MANAGER_BI  = "com.aft.encuestas.service.manager.doEncuesta.ManagerBiEncuestas.java";
	public static final String CLASS_MANAGER_DAO = "com.aft.encuestas.dao.manager.daoEncuesta.Dao";
	
	public static Object getInstanceManagerBi (String className) throws ClassNotFoundException{
		return Class.forName(className);
	}
	
	
}
