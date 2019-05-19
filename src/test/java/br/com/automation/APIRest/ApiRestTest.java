package br.com.automation.APIRest;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;

public class ApiRestTest {
	
	public static Response response;

	@Test
	public void Teste (){
		
		String uriBase =  "https://postman-echo.com/get";
		
		given()
		.relaxedHTTPSValidation()
		.param("foo1", "bar1")
		.param("foo2", "bar2")
		.when()
		.get(uriBase)
		.then()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.body("headers.host",is("postman-echo.com"))
		.body("args.foo1",is( "bar1"))
		.body("args.foo2",is ( "bar2"));

	}
	
	
	@Test
	public void ValidaListaVazia(){
		
		String uriBase = "https://assineglobo.com.br/services/rest/products";
		
		given()
		.relaxedHTTPSValidation()
		.when()
		.get(uriBase)
		.then()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.body("results", hasSize(greaterThan(0)));

	}
	
	@Test
	public void ValidaDocumentosLista(){
		
		String uriBase = "https://assineglobo.com.br/services/rest/products";
		
		response =
			    when().
			        get(uriBase).
			    then().
			        contentType(ContentType.JSON).  
			    extract().response();
		
		List<String> ListaIds = response.path("id");
		
		assertThat(ListaIds,contains("AT", "CJ", "CV" , "CF", "EP", "EN" , 
									 "GC", "GL" , "GR" , "GQ" , "MC" , "PE" , "VG"));

	}
}
