package com.aft.encuestas.service.manager.doEncuesta;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.aft.encuestas.service.core.MyManagerBiDefault;
import com.aft.encuestas.dao.manager.EncuestasDaoImpl;
import com.aft.encuestas.model.Encuesta;

public class ManagerBiEncuestas extends MyManagerBiDefault{

    EncuestasDaoImpl daoImpl = new EncuestasDaoImpl();

	@SuppressWarnings("unchecked")
	public Encuesta find(Encuesta encuesta) throws Exception{

				//01.Recuperamos atributos encuesta
					daoImpl.connect();	
					encuesta = (Encuesta)daoImpl.findEncuesta(encuesta.getId_Encuesta(), encuesta.getId_Tbl_Idioma()).get(0);
				
				if ( encuesta != null ){	
				//02.Recuperamos preguntas
					daoImpl.connect();
					encuesta.setPreguntas(daoImpl.findPreguntas(encuesta.getId_Encuesta()));
			
				//03.Recuperamos las repuestas
					for (int i = 0 ; i < encuesta.getPreguntas().size() ; i ++){
						daoImpl.connect();
						encuesta.getPreguntas().get(i).setRespuestas(daoImpl.findRepuestas(encuesta.getPreguntas().get(i).getId_Pregunta(),encuesta.getId_Tbl_Idioma() ));
					}
				}
			return encuesta;
		}
	
	@SuppressWarnings("unchecked")
	public List<Encuesta> findEncuestas (String idioma) throws Exception{
		
			List<Encuesta> listEncuestas = null;
			
				daoImpl.connect();
				listEncuestas = daoImpl.findTipoEncuesta(idioma);
			
			return listEncuestas ;
	}
	
	@SuppressWarnings("unchecked")
	public List<Encuesta> findSubTiposEncuestas (String tipoEnc, String idioma)throws Exception{
		
		List<Encuesta> listEncuestas = null;
			daoImpl.connect();
			listEncuestas = daoImpl.findSubTipoEncuesta(tipoEnc, idioma);
		
		return listEncuestas ;
	}
	
	@SuppressWarnings("rawtypes")
	public boolean add(Map collection)throws Exception{
		
			Boolean result = true;

				daoImpl.add(collection);
			
			
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public boolean addAdmin(Map collection)throws Exception{
		
			Boolean result = true;

			String subTipo = ((String[])collection.get("subTipoEncuestaNew"))[0];
			String idioma  = ((String[])collection.get("idioma"))[0];
			String fechaCad  = ((String[])collection.get("fechaCad"))[0];
			
			int j = 1;
				//01.A�adimos la encuesta nueva
				
					daoImpl.addEncAdmin(((String[])collection.get("tipoEncuestaNew"))[0], subTipo, idioma, fechaCad);
				
				//02.01.A�adimos las preguntas 
					for ( int i = 0 ; i < Integer.parseInt(((String[])collection.get("numPreNew"))[0]) ; i++){
						daoImpl.addPreAdmin(subTipo, ((String[])collection.get("pre"+i))[0], idioma);
						
						//02.02. y las respuestas
							j = 1;
							while (null != ((String[])collection.get("radio_"+i+"_"+j))){
								daoImpl.addResAdmin(((String[])collection.get("pre"+i))[0], ((String[])collection.get("radio_"+i+"_"+j))[0], ((String[])collection.get("idioma"))[0]);
								j++;
							}
					}
				
			
			
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public boolean mod(Map collection)throws Exception{
		
			Boolean result = true;
				
			Iterator ite = collection.entrySet().iterator();
			while (ite.hasNext()){
				 Map.Entry ent = (Map.Entry)ite.next();
				if ( !(ent.getKey().equals("callback") || ent.getKey().equals("_") || ent.getKey().equals("action"))) {
						
						if (ent.getKey().toString().contains("pre")){
							daoImpl.modPre (ent.getKey().toString().substring(3) , ((String[])ent.getValue())[0]);
								
							
						}else if (ent.getKey().toString().contains("radio")){
							daoImpl.modRadio (ent.getKey().toString().substring(5) , ((String[])ent.getValue())[0]);
						
						}
						
				}
			}	
				
			
			
			
		return result;
	}
	
	
	public boolean del(String id)throws Exception{
		
			Boolean result = true;

				if (daoImpl.delete(id) == 0)
						result = false ;
			
		return result;
	}

    public EncuestasDaoImpl getDaoImpl() {
        return daoImpl;
    }

    public void setDaoImpl(EncuestasDaoImpl daoImpl) {
        this.daoImpl = daoImpl;
    }
}
