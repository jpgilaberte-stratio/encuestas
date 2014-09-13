package com.aft.encuestas.control.manager.doEncuesta;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aft.encuestas.service.manager.doEncuesta.ManagerBiEncuestas;
import com.aft.encuestas.control.core.MyManagerControlDefault;
import com.aft.encuestas.model.Encuesta;

import flexjson.JSONSerializer;


public class ManagerControlEncuesta extends MyManagerControlDefault {

	private static final long serialVersionUID = -5217196976191298988L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//...Declaraci�n manager, bean y recuperaci�n parametro action
			ManagerBiEncuestas manager = new ManagerBiEncuestas();
			String action = req.getParameter("action");	
			Encuesta encuesta = new Encuesta();
			
			String res = "{\"error\":\"No se puede recuperar la info.\"}";
			//tipoEncuesta - subTipoEncuesta - nivelEncuesta - nivelEncuestaDefault
		//... Flujo principal	
			if ("find".equals(action)){
				
				//...Encapsulamos los parametros del request
					encuesta.setId_Tbl_TipoEncuesta(req.getParameter("tipoEncuesta"));
					encuesta.setId_Encuesta(req.getParameter("subTipoEncuesta"));
					encuesta.setNivelEncuesta(req.getParameter("nivelEncuesta"));
					encuesta.setId_Tbl_Idioma(req.getParameter("idioma"));
					encuesta.setFechaCad(req.getParameter("fechaCad"));
					
				//...Llamamos al BI y devolvemos la encuesta rellena
				try{	
					encuesta = manager.find(encuesta);
					res = encuesta.parseToJson();
				}catch (Exception e) {
					res = "{\"error\":\"error: "+e.getMessage()+"\"}";
				}	
					System.out.println(" find ");
				
			}else if ("add".equals(action)){
				
				//...Encapsulamos los parametros del request
				try{
					if (manager.add(req.getParameterMap()))
									res = "{\"error\":\"ok\"}"; // resultado ok
				}catch (Exception e) {
					res = "{\"error\":\"error: "+e.getMessage()+"\"}";
				}	
						System.out.println(" add ");
				
			}else if ("getTipos".equals(action)){
				
				//...
				try{
					List<Encuesta> list = manager.findEncuestas(req.getParameter("idioma"));
					res = new JSONSerializer().deepSerialize(list);
				}catch (Exception e) {
					res = "{\"error\":\"error: "+e.getMessage()+"\"}";
				}
				
			}else if ("getSubTipos".equals(action)){
				
				//...
				try{
					List<Encuesta> list = manager.findSubTiposEncuestas(req.getParameter("tipoEncuesta"), req.getParameter("idioma"));
					res = new JSONSerializer().deepSerialize(list);
					list.add(encuesta);
				}catch (Exception e) {
					res = "{\"error\":\"error: "+e.getMessage()+"\"}";
				}
					
			}else if ("addServer".equals(action)){
				try{	
					if (manager.addAdmin(req.getParameterMap()))
							res = "{\"error\":\"ok\"}"; // resultado ok
				}catch (Exception e) {
					res = "{\"error\":\"error: "+e.getMessage()+"\"}";
				}
				System.out.println(" modificar ");	
					
			}else if ("modServer".equals(action)){
				try{
					if (manager.mod(req.getParameterMap()))
							res = "{\"error\":\"ok\"}"; // resultado ok
				
				}catch (Exception e) {
					res = "{\"error\":\"error: "+e.getMessage()+"\"}";
				}
				System.out.println(" modificar ");	
					
			}else if ("delServer".equals(action)){
				try{
					if (manager.del(req.getParameter("subTipoEncuesta")))
							res = "{\"error\":\"ok\"}"; // resultado ok
				}catch (Exception e) {
					res = "{\"error\":\"error: "+e.getMessage()+"\"}";
				}
				
				System.out.println(" eliminar ");	
					
			}else if ("grafico".equals(action)){
				
				//...
				//...Encapsulamos los parametros del request
				encuesta.setId_Tbl_TipoEncuesta(req.getParameter("tipoEncuesta"));
				encuesta.setId_Encuesta(req.getParameter("subTipoEncuesta"));
				encuesta.setNivelEncuesta(req.getParameter("nivelEncuesta"));
				encuesta.setId_Tbl_Idioma(req.getParameter("idioma"));
				
			//...Llamamos al BI y devolvemos la encuesta rellena
				try{
					encuesta = manager.find(encuesta);
					res = encuesta.parseToJson();
				}catch (Exception e) {
					res = "{\"error\":\"error: "+e.getMessage()+"\"}";
				}
				System.out.println(" graphics ");	
					
			}
			//Evitar el crossDomain en el cliente
//			if (!res.contains("error"))
//			{
				res = req.getParameter("callback") + "("+ res +")"; 		
//			}
			
		resp.setHeader("content-type", "aplication-json");
		resp.getOutputStream().println(res);
	}	
}
