package com.aft.encuestas.dao.manager;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.aft.encuestas.model.Encuesta;
import com.aft.encuestas.model.Pregunta;
import com.aft.encuestas.model.Respuesta;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class EncuestasDaoImpl {

	

		/**
		 * DataSource
		 */
			MysqlDataSource ds = null;
		    
		/**
		 * Constantes conexi�n	
		 */
			private static final String JDBC_SERVER 	= "localhost";
		    private static final String JDBC_USER 		= "user_encuestas";
		    private static final String JDBC_PASS 	 	= "encuestas";
		    private static final String JDBC_RESOURCE   = "encuestasdb"; 
		    private static final String JDBC_PORT   	= "3306"; 
	    
		/**
		 * Constantes exception y finally
		 */
		    //private static final String CONNECT_CATCH = "No se puede crear la conexion. Error intentando conectar.";
		    //private static final String FIND_CATCH = "No se pueden recuperar las ocurrencias de la bbdd.";
		    private static final String DISCONNECT_CATCH = "No se puede desconectar de la bbdd ";
		    //private static final String INSERT_CATCH = "No se pueden insertar los valores en la bbdd.";
		    //private static final String UPDATE_CATCH = "No se pueden actualizar los valores en la bbdd.";
		    
		/**
		 * Constantes para las Querys, Update e Inserts
		 */
		    private static final String FIND_ENCUESTA_QUERY = " select * from tbl_encuesta where nombreIdEncuesta = (select nombreIdEncuesta from tbl_encuesta where id_Encuesta = ?) and id_tbl_idioma = (select id_idioma from tbl_idioma where tipo = ?) and fechaCad > curdate()";
		    private static final String FIND_TIPO_ENCUESTA_QUERY = " select enc.*  from  tbl_tipoencuesta enc, tbl_idioma idi where enc.id_Tbl_Idioma = idi.id_Idioma and idi.tipo = ?"; //idioma
		    private static final String FIND_SUBTIPO_ENCUESTA_QUERY = " SELECT enc.*  FROM tbl_encuesta enc, tbl_idioma idi WHERE enc.id_tbl_idioma = idi.id_idioma AND idi.tipo =  ? AND enc.Id_Tbl_TipoEncuesta = ? and fechaCad > curdate()"; //idioma
		    private static final String FIND_PREGUNTAS_QUERY = " select * from tbl_preguntas where id_Encuesta = ?";
		    private static final String FIND_RESPUESTAS_QUERY = " SELECT res.*  FROM tbl_respuestas res WHERE  res.id_pregunta = ? and res.id_tbl_idioma = ?";
		    
		    
		    
		    private static final String UPDATE_CONTADOR_QUERY = "update tbl_respuestas set contador = contador + 1 where Id_Respuesta = ?";
		  
		    private static final String UPDATE_QUERY_RADIO = "UPDATE  tbl_respuestas set respuesta = ? where  id_Respuesta = ?";
		    private static final String UPDATE_QUERY_PRE = "UPDATE tbl_preguntas set pregunta = ? where  id_Pregunta = ?";
		    private static final String DELETE_PRE = "delete from tbl_encuesta where id_Encuesta = ?";
		    
		    private static final String INSERT_QUERY_ENC   = "insert into tbl_encuesta ( Id_Tbl_TipoEncuesta, nombreIdEncuesta, id_tbl_idioma, fechaCad) values ( ?, ?, (select id_idioma from tbl_idioma where tipo = ?), ?)";
		    private static final String INSERT_QUERY_RADIO = "insert into tbl_respuestas (Id_Pregunta, Respuesta, id_tbl_idioma, contador) values ((select id_pregunta from tbl_preguntas where Pregunta = ? and id_idioma = (select id_idioma from tbl_idioma where tipo = ?)), ?, (select id_idioma from tbl_idioma where tipo = ?), 0)";
		    private static final String INSERT_QUERY_PRE   = "insert into  tbl_preguntas  (id_encuesta, Pregunta, id_idioma) values ((select Id_Encuesta from tbl_encuesta where nombreIdEncuesta = ? and id_tbl_idioma = (select id_idioma from tbl_idioma where tipo = ?)), ?, (select id_idioma from tbl_idioma where tipo = ?))";
		    
	    /**
	     * M�todo de conexi�n con la BBDD
	     */
			public void connect () throws Exception{
			
					ds = new MysqlDataSource();
						ds.setUser(JDBC_USER);    
						ds.setPassword(JDBC_PASS);
					    ds.setServerName(JDBC_SERVER);
					    ds.setDatabaseName(JDBC_RESOURCE); 
					    ds.setPortNumber(Integer.valueOf(JDBC_PORT));
					    	
			}
		
		
		/**
		 * M�todo que ejecuta la query de b�squeda
		 * 
		 * @param filter
		 */
			@SuppressWarnings("rawtypes")
			public List findEncuesta (String id, String idioma) throws Exception{
				
				List<Encuesta> result = null;
					try {		
						//00.Comprobamos la validez del datsource
							if (ds == null)   this.connect();
						
						//01.Preparamos el entorno
							QueryRunner qr = new QueryRunner(ds);
							//ResultSetHandler<List<Encuesta>> rsh = new BeanListHandler<Encuesta>(Encuesta.class);
							ResultSetHandler<List<Encuesta>> rsh = new BeanListHandler<Encuesta>(Encuesta.class);
						//02.Lanzamos la query
							result = qr.query( FIND_ENCUESTA_QUERY , rsh , id, idioma);
						
					}finally {			    
					    try{
					    	DbUtils.close(ds.getConnection()); 
		
					    }catch (Exception e) {
							System.out.println( DISCONNECT_CATCH );
						}
					}
				return result;
			}
			
			/**
			 * M�todo que ejecuta la query de b�squeda
			 * 
			 * @param filter
			 */
				@SuppressWarnings("rawtypes")
				public List findTipoEncuesta (String idioma) throws Exception{
					
					List<Encuesta> result = null;
						try {		
							//00.Comprobamos la validez del datsource
								if (ds == null)   this.connect();
							
							//01.Preparamos el entorno
								QueryRunner qr = new QueryRunner(ds);
								//ResultSetHandler<List<Encuesta>> rsh = new BeanListHandler<Encuesta>(Encuesta.class);
								ResultSetHandler<List<Encuesta>> rsh = new BeanListHandler<Encuesta>(Encuesta.class);
							//02.Lanzamos la query
								result = qr.query( FIND_TIPO_ENCUESTA_QUERY , rsh, idioma );
							
						}finally {			    
						    try{
						    	if (ds!=null){
						    		
						    		DbUtils.close(ds.getConnection());
						    	}
						    	 
			
						    }catch (Exception e) {
								System.out.println( DISCONNECT_CATCH );
							}
						}
					return result;
				}
				
				/**
				 * M�todo que ejecuta la query de b�squeda
				 * 
				 * @param filter
				 */
					@SuppressWarnings("rawtypes")
					public List findSubTipoEncuesta (String filter, String idioma) throws Exception{
						
						List<Encuesta> result = null;
							try {		
								//00.Comprobamos la validez del datsource
									if (ds == null)   this.connect();
								
								//01.Preparamos el entorno
									QueryRunner qr = new QueryRunner(ds);
									//ResultSetHandler<List<Encuesta>> rsh = new BeanListHandler<Encuesta>(Encuesta.class);
									ResultSetHandler<List<Encuesta>> rsh = new BeanListHandler<Encuesta>(Encuesta.class);
								//02.Lanzamos la query
									result = qr.query( FIND_SUBTIPO_ENCUESTA_QUERY , rsh, idioma , filter  );
								
							}finally {			    
							    try{
							    	DbUtils.close(ds.getConnection()); 
				
							    }catch (Exception e) {
									System.out.println( DISCONNECT_CATCH );
								}
							}
						return result;
					}
			/**
			 * M�todo que ejecuta la query de b�squeda
			 * 
			 * @param filter
			 */
				@SuppressWarnings("rawtypes")
				public List findPreguntas (String filter) throws Exception{
					
					List<Pregunta> result = null;
						try {		
							//00.Comprobamos la validez del datsource
								if (ds == null)   this.connect();
							
							//01.Preparamos el entorno
								QueryRunner qr = new QueryRunner(ds);
								ResultSetHandler<List<Pregunta>> rsh = new BeanListHandler<Pregunta>(Pregunta.class);
								
							//02.Lanzamos la query
								result = qr.query( FIND_PREGUNTAS_QUERY , rsh, filter);
							
						}finally {			    
						    try{
						    	DbUtils.close(ds.getConnection()); 
			
						    }catch (Exception e) {
								System.out.println( DISCONNECT_CATCH );
							}
						}
					return result;
				}
				
				/**
				 * M�todo que ejecuta la query de b�squeda
				 * 
				 * @param filter
				 */
					@SuppressWarnings("rawtypes")
					public List findRepuestas (String filter, String idioma)throws Exception{
						
						List<Respuesta> result = null;
							try {		
								//00.Comprobamos la validez del datsource
									if (ds == null)   this.connect();
								
								//01.Preparamos el entorno
									QueryRunner qr = new QueryRunner(ds);
									ResultSetHandler<List<Respuesta>> rsh = new BeanListHandler<Respuesta>(Respuesta.class);
									
								//02.Lanzamos la query
									result = qr.query( FIND_RESPUESTAS_QUERY , rsh, filter, idioma);
								
							}finally {			    
							    try{
							    	DbUtils.close(ds.getConnection()); 
				
							    }catch (Exception e) {
									System.out.println( DISCONNECT_CATCH );
								}
							}
						return result;
					}
					
			/**
			 * M�todo que ejecuta el insert 
			 * 
			 * @param filter
			 */
				@SuppressWarnings("rawtypes")
				public int add (Map collection)throws Exception{
					
					int result = 0;
					QueryRunner qr = null;
					
						try
						{	
							//00.Comprobamos la validez del datsource
								if (ds == null)   this.connect();
						
							//01.Preparamos el entorno
								qr = new QueryRunner( ds );
							   
							//02.Lanzamos el update	
								Iterator ite = collection.entrySet().iterator();
									while (ite.hasNext()){
										 Map.Entry ent = (Map.Entry)ite.next();
										if ( !(ent.getKey().equals("callback") || ent.getKey().equals("_") || ent.getKey().equals("action"))) {
							
													result = qr.update( UPDATE_CONTADOR_QUERY , ((String[])ent.getValue())[0] );
													//ds.getConnection().commit();
										}
									}
								
						  	
						}finally {			    
						    try{
						    	DbUtils.close(ds.getConnection());
			
						    }catch (Exception e) {
								System.out.println( DISCONNECT_CATCH );
						}
						}
					return result;
				}
				
				
					/**
					 * M�todo que ejecuta el update 
					 * 
					 * @param filter
					 */
						public int modRadio (String id, String value) throws Exception{
							
							int result = 0;
							QueryRunner qr = null;
							
								try
								{	
									//00.Comprobamos la validez del datsource
										if (ds == null)   this.connect();
								
									//01.Preparamos el entorno
										qr = new QueryRunner( ds );
									   
									//02.Lanzamos el update	
										result = qr.update( UPDATE_QUERY_RADIO ,  value , id );
								  	
								}finally {			    
								    try{
								    	DbUtils.close(ds.getConnection()); 
					
								    }catch (Exception e) {
										System.out.println( DISCONNECT_CATCH );
								}
								}
							return result;
						}
						
		/**
		 * M�todo que ejecuta el update 
		 * 
		 * @param filter
		 */
			public int modPre (String id, String value) throws Exception{
				
				int result = 0;
				QueryRunner qr = null;
				
					try
					{	
						//00.Comprobamos la validez del datsource
							if (ds == null)   this.connect();
					
						//01.Preparamos el entorno
							qr = new QueryRunner( ds );
						   
						//02.Lanzamos el update	
							result = qr.update( UPDATE_QUERY_PRE , value , id );
					  	
					}finally {			    
					    try{
					    	DbUtils.close(ds.getConnection()); 
		
					    }catch (Exception e) {
							System.out.println( DISCONNECT_CATCH );
					}
					}
				return result;
			}
			
			
		/**
		 * M�todo que ejecuta el update 
		 * 
		 * @param filter
		 */
			public int addEncAdmin (String tipo, String subTipo, String idioma, String fechaCad) throws Exception {
				
				int result = 0;
				QueryRunner qr = null;
				
					try
					{	
						//00.Comprobamos la validez del datsource
							if (ds == null)   this.connect();
					
						//01.Preparamos el entorno
							qr = new QueryRunner( ds );
						   
						//02.Lanzamos el update	
							result = qr.update( INSERT_QUERY_ENC , tipo , subTipo, idioma, fechaCad );
					  	
					}
					finally {			    
					    try{
					    	DbUtils.close(ds.getConnection()); 
		
					    }catch (Exception e) {
							System.out.println( DISCONNECT_CATCH );
					}
					}
				return result;
			}
			
			
			/**
			 * M�todo que ejecuta el update 
			 * 
			 * @param filter
			 */
				public int addPreAdmin (String idEnc, String pre, String idioma)throws Exception{
					
					int result = 0;
					QueryRunner qr = null;
					
						try
						{	
							//00.Comprobamos la validez del datsource
								if (ds == null)   this.connect();
						
							//01.Preparamos el entorno
								qr = new QueryRunner( ds );
							   
							//02.Lanzamos el update	
								result = qr.update( INSERT_QUERY_PRE , idEnc ,idioma, pre, idioma );
						  	
						}finally {			    
						    try{
						    	DbUtils.close(ds.getConnection()); 
			
						    }catch (Exception e) {
								System.out.println( DISCONNECT_CATCH );
						}
						}
					return result;
				}
				/**
				 * M�todo que ejecuta el update 
				 * 
				 * @param filter
				 */
					public int addResAdmin (String idPre, String res, String idioma)throws Exception{
						
						int result = 0;
						QueryRunner qr = null;
						
							try
							{	
								//00.Comprobamos la validez del datsource
									if (ds == null)   this.connect();
							
								//01.Preparamos el entorno
									qr = new QueryRunner( ds );
								   
								//02.Lanzamos el update	
									result = qr.update( INSERT_QUERY_RADIO , idPre , idioma, res, idioma );
							  	
							}finally {			    
							    try{
							    	DbUtils.close(ds.getConnection()); 
				
							    }catch (Exception e) {
									System.out.println( DISCONNECT_CATCH );
							}
							}
						return result;
					}		
		/**
		 * M�todo que ejecuta el update 
		 * 
		 * @param filter
		 */
			public int delete (String idPregunta)throws Exception{
				
				int result = 0;
				QueryRunner qr = null;
				
					try
					{	
						//00.Comprobamos la validez del datsource
							if (ds == null)   this.connect();
					
						//01.Preparamos el entorno
							qr = new QueryRunner( ds );
						   
						//02.Lanzamos el update	
							result = qr.update( DELETE_PRE , idPregunta );
					  	
					}finally {			    
					    try{
					    	DbUtils.close(ds.getConnection()); 
		
					    }catch (Exception e) {
							System.out.println( DISCONNECT_CATCH );
					}
					}
				return result;
			}
}