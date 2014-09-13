

var Translate2 = function (local){
	
	this.localdefault = "es";
	
this.local = local;

this.Menu_Principal_es="Menu";
this.Encuestas_es="Encuestas";	
this.Realizar_encuestas_es="Realizar Encuestas";
this.Tipo_es= "Tipo";
this.Sub_es= "Subtipo";
this.Nivel_es = "Nivel" ;
this.Nivel_Defecto_es = "Nivel por Defecto" ;
this.Encuesta_Aleatoria_es= "Encuesta Aleatoria";
this.solicitar_es="Solicitar";
this.Si_es= "Si"
this.grafos_es="graficos";
this.tipoEncuestaLabel_es = "Tipo encuesta";
this.subTipoEncuestaLabel_es = "SubTipo encuesta";



this.Menu_Principal_uk="Main";	
this.Encuestas_uk="Polls";	
this.Realizar_encuestas_uk="Make Polls";
this.Tipo_uk= "Type";
this.Sub_uk= "Subtype";
this.Nivel_uk = "Level" ;
this.Nivel_Defecto_uk = "Default level" ;
this.Encuesta_Aleatoria_uk= "Random survey";
this.solicitar_uk="Request";
this.Si_uk="Yes";
this.grafos_uk="Graphics";
this.tipoEncuestaLabel_uk = "Type polls";
this.subTipoEncuestaLabel_uk = "SubType polls";

this.Menu_Principal_it="Menu Principale";	
this.Encuestas_it="Sondaggi";	
this.Realizar_encuestas_it="Fai Sondaggi";
this.Tipo_it= "Tipo";
this.Sub_it= "Sottotipo";
this.Nivel_it = "Livello" ;
this.Nivel_Defecto_it = "Predefinito Livello" ;
this.Encuesta_Aleatoria_it= "Indagine Casuale";
this.solicitar_it="Richiesta";
this.Si_it="Yes";
this.grafos_it="Grafica";
this.tipoEncuestaLabel_it = "Tip sondaggi";
this.subTipoEncuestaLabel_it = "SubTip sondaggi";


this.Menu_Principal_fr="Menu Principale";	
this.Encuestas_fr="Sondages";	
this.Realizar_encuestas_fr="Faire Sondages";
this.Tipo_fr= "Type";
this.Sub_fr= "Subtype";
this.Nivel_fr = "Niveau";
this.Nivel_Defecto_fr = "Niveau par défaut";
this.Encuesta_Aleatoria_fr="Random Survey";
this.solicitar_fr="Demander"
this.Si_fr="Oui";
this.grafos_fr="Graphique";
this.tipoEncuestaLabel_fr = "Tipe sondages";
this.subTipoEncuestaLabel_fr = "SubTipe sondages";

this.Menu_Principal_component="#Menu_Principal";
this.Encuestas_component="#Encuestas";
this.Realizar_encuestas_component="#Realizar_encuestas";
this.Tipo_component= "#Tipo";
this.Sub_component= "#Sub";			
this.Nivel_component="#Nivel";
this.Nivel_Defecto_component="#Nivel_Defecto";
this.Encuesta_Aleatoria_component="#Encuesta_Aleatoria";
this.solicitar_component="#solicitar";
this.Si_component="#Si";
this.grafos_component="#grafos";
this.tipoEncuestaLabel_component = "#tipoEncuestaLabel";
this.subTipoEncuestaLabel_component = "#subTipoEncuestaLabel";







	
	
	this.translate2local=function () {
		
		
			var dictionary = new Translate2(this.local);
			
				
		
				$(dictionary.Encuestas_component).html(eval("dictionary.Encuestas_" +dictionary.local));
				$(dictionary.Menu_Principal_component).html(eval("dictionary.Menu_Principal_" +dictionary.local));
				$(dictionary.Realizar_encuestas_component).html(eval("dictionary.Realizar_encuestas_" +dictionary.local));
				$(dictionary.Tipo_component).html(eval("dictionary.Tipo_" +dictionary.local));
				$(dictionary.Sub_component).html(eval("dictionary.Sub_" +dictionary.local));
				$(dictionary.Nivel_component).html(eval("dictionary.Nivel_" +dictionary.local));
				$(dictionary.Nivel_Defecto_component).html(eval("dictionary.Nivel_Defecto_" +dictionary.local));
				$(dictionary.Encuesta_Aleatoria_component).html(eval("dictionary.Encuesta_Aleatoria_" +dictionary.local));
				$(dictionary.solicitar_component).html(eval("dictionary.solicitar_" +dictionary.local));
				$(dictionary.Si_component).html(eval("dictionary.Si_" +dictionary.local));
				$(dictionary.grafos_component).html(eval("dictionary.grafos_" +dictionary.local));
				
				$(dictionary.tipoEncuestaLabel_component).html(eval("dictionary.tipoEncuestaLabel_" +dictionary.local));
				$(dictionary.subTipoEncuestaLabel_component).html(eval("dictionary.subTipoEncuestaLabel_" +dictionary.local));
				
				
				
			
				
				
		        
				
		
		}
		
}
