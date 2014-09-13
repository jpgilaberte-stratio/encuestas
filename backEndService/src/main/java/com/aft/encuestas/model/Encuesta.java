package com.aft.encuestas.model;

import java.util.List;

import com.aft.encuestas.model.core.MyModelDefault;

import flexjson.JSONSerializer;


public class Encuesta extends MyModelDefault{


	


	public String getFechaCad() {
		return fechaCad;
	}



	public void setFechaCad(String fechaCad) {
		this.fechaCad = fechaCad;
	}



	public String getId_Tbl_Idioma() {
		return id_Tbl_Idioma;
	}



	public void setId_Tbl_Idioma(String id_Tbl_Idioma) {
		this.id_Tbl_Idioma = id_Tbl_Idioma;
	}



	public String getId_TipoEncuesta() {
		return id_TipoEncuesta;
	}



	public void setId_TipoEncuesta(String id_TipoEncuesta) {
		this.id_TipoEncuesta = id_TipoEncuesta;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}





	private String id_Encuesta;
	private String id_Tbl_TipoEncuesta;
	
	private String id_TipoEncuesta;
	private String tipo;
	private String nivelEncuesta;
	private String id_Tbl_Idioma;
	private String fechaCad;

	private List<Pregunta> preguntas;
	
	

	
	
	@Override
	public String parseToJson() {
		return new JSONSerializer().deepSerialize(this);
	}
	
	
	
	public String getId_Encuesta() {
		return id_Encuesta;
	}





	public void setId_Encuesta(String id_Encuesta) {
		this.id_Encuesta = id_Encuesta;
	}





	public String getId_Tbl_TipoEncuesta() {
		return id_Tbl_TipoEncuesta;
	}





	public void setId_Tbl_TipoEncuesta(String id_Tbl_TipoEncuesta) {
		this.id_Tbl_TipoEncuesta = id_Tbl_TipoEncuesta;
	}





	public String getNombreIdEncuesta() {
		return tipo;
	}





	public void setNombreIdEncuesta(String nombreIdEncuesta) {
		this.tipo = nombreIdEncuesta;
	}





	public String getNivelEncuesta() {
		return nivelEncuesta;
	}

	public void setNivelEncuesta(String nivelEncuesta) {
		this.nivelEncuesta = nivelEncuesta;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

}
