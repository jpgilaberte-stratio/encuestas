package com.aft.encuestas.model;


import com.aft.encuestas.model.core.MyModelDefault;

import flexjson.JSONSerializer;

public class Respuesta extends MyModelDefault{

	public String getId_Respuesta() {
		return id_Respuesta;
	}
	public void setId_Respuesta(String id_Respuesta) {
		this.id_Respuesta = id_Respuesta;
	}
	public String getId_Pregunta() {
		return id_Pregunta;
	}
	public void setId_Pregunta(String id_Pregunta) {
		this.id_Pregunta = id_Pregunta;
	}
	private String id_Respuesta;
	private String id_Pregunta;
	private String respuesta;
	private String contador;
	
	
	
	
	
	
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getContador() {
		return contador;
	}
	public void setContador(String contador) {
		this.contador = contador;
	}
	@Override
	public String parseToJson() {
			
		return new JSONSerializer().serialize(this);
	}
}
