package org.edu.arep.arep.aws;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import java.util.*;
/**
 * @author Andres
 * Esta clase permite crear la aplicacion con spark
 */
public class SparkWebApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		port(getPort());
		get("/",(req,resp)->pageIndex(req,resp));
		get("/calculo",(req,resp)->Calculo(req,resp));
		get("/results",(req,resp)->answer(req, resp));
		
	}
	

	public static String pageIndex(Request req,Response resp){
		String respuesta="<!doctype html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <meta name=\"viewport\"\n" +
				"          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
				"    <title>AREP</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <h1>AREP</h1>\n" +
				"    <p>Este es un programa que calcula el cuadrado de un numero para iniciar de click en el siguiente\n" +
				"        vinculo <a href=\"\\calculo\">Oprime</a>\n" +
				"    </p>\n" +
				"</body>\n" +
				"</html>";
		return respuesta;
	}
	public static String Calculo(Request req,Response resp){
		String respuesta="<!doctype html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <meta name=\"viewport\"\n" +
				"          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
				"    <title>Document</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <h1>AREP</h1>\n" +
				"    <br/>\n" +
				"    <h2>Digita el dato de la siguiente forma</h2>\n" +
				"    <p>Ej:\n" +
				"        4\n" +
				"    </p>\n" +
				"    <form action=\"/results\">\n" +
				"        <h3>Digita el numero</h3>\n" +
				"        <input type=\"text\" placeholder=\"Numero\" name=\"DataOne\">\n" +
				"        <input type=\"submit\" value=\"Calcular\">\n" +
				"\n" +
				"    </form>\n" +
				"\n" +
				"</body>\n" +
				"</html>";
		return respuesta;
	}

	public static String answer(Request req,Response resp) throws MalformedURLException, IOException{
		String set1 = req.queryParams("DataOne");
                URL valor = new URL("https://3x6qopwn3h.execute-api.us-east-1.amazonaws.com/cuadrado?value="+set1);
                String calval="";
                String linea;
                BufferedReader in = new BufferedReader(new InputStreamReader(valor.openStream()));
                while ((linea=in.readLine()) != null) {
                    calval+=linea;
                }
		String respuesta="<!doctype html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <meta name=\"viewport\"\n" +
				"          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
				"    <title>Document</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <h1>AREP</h1>\n" +
				"\n"+
				"El valor es "+calval+"\n"+
                                "<br>"+
				"\n" +
				"\n" +
				"\n" +
				"</body>\n" +
				"</html>";
		return respuesta;
	}

	/**
	 * Este metodo permite tener el puerto por defecto de heroku o asignarle uno
	 * 
	 * @return
	 */
	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 4567; // returns default port if heroku-port isn't set (i.e. on localhost)
	}


}