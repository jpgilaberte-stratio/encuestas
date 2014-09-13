package com.aft.encuestas.model;

import java.util.ArrayList;
import java.util.List;

import com.aft.encuestas.model.core.MyModelDefault;

import flexjson.JSONSerializer;

public class Pregunta extends MyModelDefault{
	
	
	public String getId_Pregunta() {
		return id_Pregunta;
	}


	public void setId_Pregunta(String id_Pregunta) {
		this.id_Pregunta = id_Pregunta;
	}


	public String getId_Encuesta() {
		return id_Encuesta;
	}


	public void setId_Encuesta(String id_Encuesta) {
		this.id_Encuesta = id_Encuesta;
	}

	private String id_Pregunta;
	private String id_Encuesta;
	private String pregunta;
	private String nivel;
	
	private List<Respuesta> respuestas;
	
	public Pregunta () {
		respuestas = new ArrayList<Respuesta>();
	}
	
	
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public List<Respuesta> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	@Override
	public String parseToJson() {
		
		return new JSONSerializer().deepSerialize(this);
	}
}
