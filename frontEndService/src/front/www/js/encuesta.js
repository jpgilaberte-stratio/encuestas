/**
 * 
 */

 
var Encuesta = function(){
	
	this.idio = new Translate2("es");
	this.est = new Estilos("-c ", "c");
	this.url= "http://localhost:8080/Encuestas/doEncuesta?";
	
	/**
	 * 
	 */
		this.getTipos = function () {
			
			var idioma = this.idio.local;
			llamadaAjax (this.url+ "action=getTipos" , "&idioma="+idioma, 
					function (json){
							$.each(json, function (index, value){			
								//...Seteamos el subtipo y comprobamos que el tipo no este repetido 		
									$("#tipoEncuesta").append("<option  value='"+value.id_TipoEncuesta+"'>"+value.tipo+"</option>");
									
							});						
							$.mobile.changePage("#pageConfigurarEncuesta");
						});
		};
	
	
	/**
	 * Obtener subtipos
	 */
		this.getSubTipos = function () {
			
			var idioma = this.idio.local;
			llamadaAjax (this.url+ "action=getSubTipos" , "&tipoEncuesta="+$("#tipoEncuesta option:selected").val() + "&idioma="+idioma, 
					function (json){
							$("#subTipoEncuesta").html("");
							$("#subTipoEncuesta").append(" <option value='0'></option>");
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
		this.solicitarEncuesta = function () {	
		
			var estLocal = this.est.temaDefault;
			var idioma = this.idio.local;
				
				if ( $('#subTipoEncuesta').val() == 0){
					return false;
				}
				if($.cookie('encuesta'+$('#subTipoEncuesta').val())){
					alert('Esta encuesta ya ha sido votada.');
					return false;
				}
					
				llamadaAjax (this.url +"action=find" , $("#formSolicitar").serialize() + "&idioma=" + idioma , 
						
						function (json) {
							
							$.each (json.preguntas, function (index, value){
									
									var code =  "<div id='div"+index+"' data-role='page'  >" ;
												
										
									code    +=  '<div id="header" data-theme="a" data-role="header" data-position="fixed">'+			
												'<h3> '+
		            							'	ON-ENCUESTAS  '+
		            							'</h3> '+
		            							'</div> ';
												
		            				code    +=	" <center> <h2>"+value.pregunta+"</h2></center>"+
		            							"<div data-role='content'>" +
		            							"<form id='formu"+index+"' name='formu"+index+"' >";
												$.each(value.respuestas, function(subIndex, subValue){
													code += "<input id='radio"+subValue.id_Respuesta+"' data-theme='"+estLocal+"' name='"+subValue.id_Pregunta+"' value='"+subValue.id_Respuesta+"' type='radio'>"+
															"<label for='radio"+subValue.id_Respuesta+"'>"+subValue.respuesta+"</label>";
												});
												
									code    +=  "<br>" ; 
										
										if (index != (json.preguntas.length - 1)) code += "<a data-role='button' data-theme='"+estLocal+"' data-iconpos='bottom' data-icon='arrow-r' href='#div"+(index+1)+"'></a>";
										else code += "<a data-role='button' data-icon='check' data-iconpos='bottom' data-theme='"+estLocal+"' onClick='manager.sendEncuesta();'></a>";
										
									code    +=  "<a data-role='button' data-theme='"+estLocal+"' data-icon='delete' data-iconpos='bottom' onClick='manager.cancelEncuesta();'></a>";
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
	 * Solicitar encuesta
	 */
		this.solicitarGrafico = function () {	
		
			var estLocal = this.est.temaDefault;
			var idioma = this.idio.local;
				
				llamadaAjax (this.url+"action=grafico" , $("#formSolicitar").serialize() + "&idioma=" + idioma , 
						
						function (json) {
							$("#graficos").html("");
							$.each (json.preguntas, function (index, value){
									
									var code    =	" <br><center> <div id='grafico"+index+"' style='width:400px; height:225px;'></div> </center></br>";
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
		};

			
		this.pru = function (){
				
		
$.plot(
   $("#grafico"),
   [
    {
      label: "te gusta cocinar con la termomix ? ",
      data: [ [2011, 2], [2012, 0], [2013, 3], [2014, 4] ],
      bars: {
        show: true,
        barWidth: 0.1,
        align: "center"
      }   
    }
 ],
 {
   xaxis: {
     ticks: [
       [2011, "si"],
       [2012, "no"],
       [2013, "ns"],
       [2014, "ddd"]
     ]
   }   
 }
);
//$.plot(
//$("#flot-example-3"),
//[
//  {
//    label: "This",
//    data: 44   
//  },
//  {
//    label: "That",
//    data: 75
//  },
//  {
//    label: "The Other Thing",
//    data: 22
//  }
//],
//{
//  series: {
//    pie: {
//      show: true,
//      label: {
//        show: true
//      }
//  }
// },
// legend: {
//   show: true
// }
//}
//);
//				var d1 = ["a"];
//				var d2 = [[300,200]];
//				var d3 = [[350,200]];
//				
//				$.plot($("#grafico"), [d1, d2]);
//				$.plot($("#grafico"), [d1, d3]);
				$.mobile.changePage("#pageGrafico");									
		};
		
	/**
	 *  Bot�n enviar encuesta
	 */
		this.sendEncuesta = function (){
				
				llamadaAjax (this.url+"action=add" , $("input[type=radio]:checked").serialize(), 
						
						function (json) {
							if (json.error === "ok"){
								alert("La encuesta se ha enviado correctamente.");
									//navigator.notification.vibrate(2000);
									$.cookie('encuesta'+$("#subTipoEncuesta").val(), $("#subTipoEncuesta").val(), { expires: 7 });
								}else{
									//navigator.notification.vibrate(2000);
									alert("No se puede enviar la encuesta.");
							}	
							location.reload();
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
	 * Llamada ajax gen�rica
	 */
		 function llamadaAjax  (url, data, succes){
		
			$.ajax({
				dataType: "jsonp",
				method: "GET",
				data: data,
				url: url,
				beforeSend : function (){
					$.mobile.showPageLoadingMsg("b","Cargando",false);
				},	
				success: succes
			});
	
		 };

			 
	/**
	 * 
	 */	 
		this.translate2 = function (local){	
				this.idio = new Translate2(local);
				this.idio.translate2local();
		};
		
	/**
	 * 
	 */
		this.cambioStyle = function( estilo, tema) {
				this.est.cambioEstilo( estilo , tema);
		}
		
		
}
    
