/**
 * 
 */

 
var Encuesta = function(){
	
	
	/**
	 * 
	 */
		this.getTipos = function () {
			
			llamadaAjax ("http://localhost:8080/Encuestas/doEncuesta?action=getTipos" , "&idioma=es", 
					function (json){
							$.each(json, function (index, value){			
								//...Seteamos el subtipo y comprobamos que el tipo no este repetido 		
									$("#tipoEncuesta").append("<option  value='"+value.id_TipoEncuesta+"'>"+value.tipo+"</option>");
									
							});						
							$.mobile.changePage("#pageBuscar");
						});
		};
		
	/**
	 * 
	 */
		this.getTiposNew = function () {
			
			llamadaAjax ("http://localhost:8080/Encuestas/doEncuesta?action=getTipos" , "&idioma=es", 
					function (json){
						$.each(json, function (index, value){			
								//...Seteamos el subtipo y comprobamos que el tipo no este repetido 		
									$("#tipoEncuestaNew").append("<option  value='"+value.id_TipoEncuesta+"'>"+value.tipo+"</option>");
									
							});						
							$.mobile.changePage("#pageNueva");
						});
		};
	
	/**
	 * Obtener subtipos
	 */
		this.getSubTipos = function () {
			
			llamadaAjax ("http://localhost:8080/Encuestas/doEncuesta?action=getSubTipos" , "&idioma="+$("#idioma input:checked").val()+"&tipoEncuesta="+$("#tipoEncuesta option:selected").val(), 
					function (json){
							$("#subTipoEncuesta").html("");
							$("#subTipoEncuesta").append(" <option value='0'>Subtipo encuesta</option>  ");
							$.each(json, function (index, value){			
								//...Seteamos el subtipo y comprobamos que el tipo no este repetido 
									
								$("#subTipoEncuesta").append("<option value='"+value.id_Encuesta+"'>"+value.tipo+"</option>");		
																
							});
							$.mobile.hidePageLoadingMsg("b","Cargando",false);
						});
		};
					
					
	/**
	 * Solicitar encuesta
	 */
		this.solicitarEncuesta = function (local) {	
		
				if (  $("#subTipoEncuesta").val() == 0 ) { 
					alert ("Debe configurar una encuesta antes de pulsar."); 
					return false;
				}
				
				llamadaAjax ("http://localhost:8080/Encuestas/doEncuesta?action=find" , $("#formBuscar").serialize()  , 
						
						function (json) {
							
							$.each (json.preguntas, function (index, value){
									
									var code =  "<div id='div"+index+"' data-role='page'  >" ;
												
										
									code    +=  '<div id="header" data-theme="a" data-role="header" data-position="fixed">'+			
												'<h3> '+
		            							'	ON-ENCUESTAS  '+
		            							'</h3> '+
		            							'</div> ';
												
		            				code    +=	" <input id='pre"+value.id_Pregunta+"' data-mod='mod' name='pre"+value.id_Pregunta+"' type='text' value='"+value.pregunta+"'>"+
		            							"<div data-role='content'>" +
		            							"<form id='formu"+index+"' name='formu"+index+"' >";
												$.each(value.respuestas, function(subIndex, subValue){
													code += "<input id='radio"+subValue.id_Respuesta+"' data-mod='mod' data-theme='c' name='radio"+subValue.id_Respuesta+"' value='"+subValue.respuesta+"' type='input'>";
												});
												
									code    +=  "<br>" ; 
										
										if (index != (json.preguntas.length - 1)) code += "<a data-role='button' data-theme='c' data-iconpos='bottom' data-icon='arrow-r' href='#div"+(index+1)+"'></a>";
										else code += "<a data-role='button' data-icon='check' data-iconpos='bottom' data-theme='c' onClick='manager.sendEncuestaMod();'></a>";
										
									code    +=  "<a data-role='button' data-theme='c' data-icon='delete' data-iconpos='bottom' onClick='manager.cancelEncuesta();'></a>";
									code    +=  "</form></div>";  
									code    +=  '<div data-theme="a" data-role="footer" data-position="fixed">'+
								        		'<h3> '+
		            							'	 '+
		            							'</h3> '+
		            							'</div> ';  
									code    +=  "</div>";
									
									$("body").append( code ) ;
							});
							
							$.mobile.changePage( "#div0" );
						
						});
		};

			
		/**
	 *  Bot�n enviar encuesta
	 */
		this.sendEncuestaMod = function (){
				
				if (  $("#tipoEncuesta").val() == 0 ) { 
					alert ("Debe configurar una encuesta antes de pulsar."); 
					return false;
				}
				
				llamadaAjax ("http://localhost:8080/Encuestas/doEncuesta?action=modServer" , $("input[data-mod=mod]").serialize() + "&idioma=" + $("#idiomaNew input:checked").val(), 
						
						function (json) {
							if (json.error === "ok")
								alert("La encuesta se ha modificado correctamente.");
							else
								alert("No se puede modificar la encuesta.");
								
						location.reload();
						});
		};
/**
	 * Solicitar encuesta
	 */
		this.rellenarNueva = function (local) {	
		
				if (  $("#tipoEncuestaNew").val() == 0 ) { 
					alert ("Debe configurar una encuesta antes de pulsar."); 
					return false;
				}
				
						for (var i = 0 ; i < $("#numPreNew").val() ; i++){			
									var code =  "<div id='div"+i+"' data-role='page'  >" ;
												
										
									code    +=  '<div id="header" data-theme="a" data-role="header" data-position="fixed">'+			
												'<h3> '+
		            							'	ON-ENCUESTAS  '+
		            							'</h3> '+
		            							'</div> ';
												
		            				code    +=	" <input id='pre"+i+"' data-new='new' name='pre"+i+"' type='text' value=''>"+
		            							"<div data-role='content'>" ;
		            						
									code    +=  "<div id='preg"+i+"'>"+
													"<input id='radio_"+i+"_1' data-new='new' data-theme='c' name='radio_"+i+"_1' value='' type='input'>"+
													"<input id='radio_"+i+"_2' data-new='new' data-theme='c' name='radio_"+i+"_2' value='' type='input'>"+
												"</div>"+
												"<a data-role='button' data-theme='c' data-icon='add' data-iconpos='bottom' onClick='manager.addResp(\""+i+"\");'></a>";

									code    +=  "<br>" ; 
										
										if (i != ($("#numPreNew").val() - 1)) code += "<a data-role='button' data-theme='c' data-iconpos='bottom' data-icon='arrow-r' href='#div"+(i+1)+"'></a>";
										else code += "<a data-role='button' data-icon='check' data-iconpos='bottom' data-theme='c' onClick='manager.sendEncuesta();'></a>";
										
									code    +=  "<a data-role='button' data-theme='c' data-icon='delete' data-iconpos='bottom' onClick='manager.cancelEncuesta();'></a>";
									code    +=  "</form></div>";  
									code    +=  '<div data-theme="a" data-role="footer" data-position="fixed">'+
								        		'<h3> '+								       
		            							'</h3> '+
		            							'</div> ';  
									code    +=  "</div>";
									
									$("body").append( code ) ;
						}
							$.mobile.changePage( "#div0" );		
		};
	
	/**
	 * 
	 */
	this.addResp = function (i){
		
		$("#preg"+i).append("<input id='radio_"+i+"_"+($("#preg"+i+" input").length+1)+"' data-new='new' data-theme='c' name='radio_"+i+"_"+($("#preg"+i+" input").length+1)+"' value='' type='input'>");
		
	};
	/**
	 *  Bot�n enviar encuesta
	 */
		this.sendEncuesta = function (){
				
//				if (  $("#tipoEncuesta").val() == 0 ) { 
//					alert ("Debe configurar una encuesta antes de pulsar."); 
//					return false;
//				}
				
				llamadaAjax ("http://localhost:8080/Encuestas/doEncuesta?action=addServer" , $("input[data-new=new]").serialize() + "&tipoEncuestaNew=" + $("select[data-new=new] option:selected").val() + "&idioma=" + $("#idiomaNew input:checked").val(), 
						
						function (json) {
							if (json.error === "ok")
								alert("La encuesta se ha creado correctamente.");
							else
								alert("No se puede crear la encuesta.");
								
						location.reload();
						});
		};
		
	
	/**
	 *  Bot�n enviar encuesta
	 */
		this.delEncuesta = function (){
				
				if (  $("#subTipoEncuesta").val() == 0 ) { 
					alert ("Debe configurar una encuesta antes de pulsar."); 
					return false;
				}
				
				llamadaAjax ("http://localhost:8080/Encuestas/doEncuesta?action=delServer" , $("#subTipoEncuesta").serialize(), 
						
						function (json) {
							if (json.error === "ok"){
								alert("La encuesta se ha eliminado correctamente.");
								$.mobile.changePage( "#pageMenu" );
							}else{
								alert("No se puede eliminar la encuesta.");
							}	
						
						});
		};
		
	 /**
	 *  Bot�n cancelar encuesta
	 */
		this.cancelEncuesta = function (){
			
				$.mobile.changePage( "#pageMenu" );
				location.reload();

		};
	
	/**
	 * Solicitar encuesta
	 */
		this.solicitarGrafico = function () {	
			
			if ($("#tipoEncuesta").val() == 0 || $("#subTipoEncuesta").val() == 0 ) {
				alert("Seleccione una encuesta.");
				return false;
			}
				llamadaAjax ("http://localhost:8080/Encuestas/doEncuesta?action=grafico" , "&tipoEncuesta=" + $("#tipoEncuesta").val() + "&subTipoEncuesta=" + $("#subTipoEncuesta").val() + "&idioma=" + $("#idioma input:checked").val() , 
						
						function (json) {
							$("#graficos").html("");
							$.each (json.preguntas, function (index, value){
									
									var code    =	" <div id='grafico"+index+"' style='width:300px; height:250px;'></div> ";
									$("#graficos").append( code ) ;
									var comp = "#grafico"+index;
									var pre  = value.pregunta;
									var dataData = [];
									var dataAxis = [];
												$.each(value.respuestas, function(subIndex, subValue){
													
													dataData.push ([subValue.id_Respuesta, subValue.contador]);
													dataAxis.push ([subValue.id_Respuesta, subValue.respuesta]);
												});
												
												$.plot(
												   $(comp),
												   [
												    {
												      label: pre,
												      data: dataData,
												      bars: {
												        show: true,
												        barWidth: 0.2,
												        align: "center"
												      }   
												    }
												 ],
												 {
												   xaxis: {
												     ticks: dataAxis
												   }   
												 }
												);

									var code2    =  "<br><br><p style='padding=10px'></p><br><br>" ; 
									
									
									$( comp ).append( code2 ) ;
							});
							
							$.mobile.changePage( "#pageGrafico" );
						
						});
						
										$.mobile.changePage("#pageGrafico");
		};


	/**
	 * Error generico 
	 */
	function errorGenerico(){
		
		alert("Se ha producido un error, reinicie la aplicación.");
		$.mobile.hidePageLoadingMsg("b","Cargando",true);
	}
			
	/**
	 * Llamada ajax gen�rica
	 */
		 function llamadaAjax  (url, data, succes){
		
			$.ajax({
				dataType: "jsonp",
				method: "GET",
				timeout: 5000,
				data: data,
				url: url,
				beforeSend : function (){
					$.mobile.showPageLoadingMsg("b","Cargando",false);
				},	
				success: succes,
				error: errorGenerico,
			});
	
		 };
		
}
    
