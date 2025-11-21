package com.DevArt.Usuarios;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.DevArt.Usuarios.controller.UsuariosController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

// Java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = { "spring.profiles.active=dev" })
public class UsuariosApplicationTests {

	@Autowired
	private UsuariosController usuarioController;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		System.out.println("Testing the context loading...");
	}

	@Test
	public void contextLoads2() throws Exception {
		System.out.println("Testing the context loading. and the controller...");
		assertThat(usuarioController).isNotNull();
	}

	// Get
	@Test
	public void testGetUsuarios() throws Exception {
		System.out.println("Testing the getUsuarios method...");
		String url = "http://localhost:" + port + "/api/v1/usuarios";
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).contains("usuarios");
		System.out.println("Response: " + response);
	}

	// Get
	@Test
	public void testGetUsuariosByRut() throws Exception {
		System.out.println("Testing the getUsuariosById method...");
		String url = "http://localhost:" + port + "/api/v1/usuarios/22.222.222-4";
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).contains("rut");
		System.out.println("Response: " + response);
	}

	// Get
	@Test
	public void testGetUsuarioPrimerNombre() throws Exception {
		System.out.println("Testing the getUsuarioPrimerNombre method...");
		String url = "http://localhost:" + port + "/api/v1/usuarios/nombre/Aaron";
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).contains("Aaron");
		System.out.println("Response: " + response);
	}

	// Get
	@Test
	public void testGetUsuarioSegundoNombre() throws Exception {
		System.out.println("Testing the getUsuarioSegundoNombre method...");
		String url = "http://localhost:" + port + "/api/v1/usuarios/segundoNombre/Josue";
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).contains("Josue");
		System.out.println("Response: " + response);
	}

	// Get
	@Test
	public void testGetUsuarioPrimerApellido() throws Exception {
		System.out.println("Testing the getUsuarioPrimerApellido method...");
		String url = "http://localhost:" + port + "/api/v1/usuarios/primerApellido/Lorca";
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).contains("Lorca");
		System.out.println("Response: " + response);
	}

	// Get
	@Test
	public void testGetUsuarioSegundoApellido() throws Exception {
		System.out.println("Testing the get usuario segundo apellido method");
		String url = "http://localhost:" + port + "/api/v1/usuarios/segundoApellido/Donoso";
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).contains("Donoso");
	}

	// Get
	@Test
	public void testGetUsuarioCorreo() throws Exception {
		System.out.println("Testing de usuario correo method");
		String url = "http://localhost:" + port + "/api/v1/usuarios/correo/aa.lorca@duocuc.cl";
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).contains("aa.lorca@duocuc.cl");
	}



	// Get Telefono
	@Test
	public void testGetUsuarioTelefono() throws Exception {
		System.out.println("Testing the getUsuarioTelefono method...");
		String url = "http://localhost:" + port + "/api/v1/usuarios/telefono/123456789";
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).contains("123456789");
		System.out.println("Response: " + response);
	}

	// Creacion de Post
	@Test
	public void testCreateUsuario() throws Exception {
		System.out.println("Testing the createUsuario method...");
		String url = "http://localhost:" + port + "/api/v1/usuarios";

		String newUsuario = "{" +
				"\"rut\":\"33.333.333-3\"," +
				"\"cargo\":\"Analista\"," +
				"\"primerNombre\":\"Juan\"," +
				"\"segundoNombre\":\"Carlos\"," +
				"\"primApellido\":\"Perez\"," +
				"\"segApellido\":\"Gonzalez\"," +
				"\"telefono\":\"3323\"," +
				"\"correo\":\"juan.perez@email.com\"," +
				"\"fechaNacimiento\":\"1990-01-01\"," +
				"\"rol\":\"Usuario\"," +
				"\"departamento\":\"TI\"" +
				"}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(newUsuario, headers);

		ResponseEntity<String> response = this.restTemplate.postForEntity(url, request, String.class);
		assertThat(response.getBody()).contains("33.333.333-3");
		assertThat(response.getBody()).contains("Juan");
		assertThat(response.getBody()).contains("Perez");
		assertThat(response.getBody()).contains("juan.perez@email.com");
		System.out.println("Response: " + response.getBody());
	}

	// Actualizacion de Put
	@Test
	public void testUpdateUsuario() throws Exception {
		System.out.println("Testing the updateUsuario method...");
		String url = "http://localhost:" + port + "/api/v1/usuarios/33.333.333-3";

		String updatedUsuario = "{" +
				"\"rut\":\"33.333.333-3\"," +
				"\"cargo\":\"Analista Senior\"," +
				"\"primerNombre\":\"Juan\"," +
				"\"segundoNombre\":\"Carlos\"," +
				"\"primApellido\":\"Perez\"," +
				"\"segApellido\":\"Gonzalez\"," +
				"\"telefono\":\"3323\"," +
				"\"correo\":\"juan.perez@email.com\"," +
				"\"fechaNacimiento\":\"1990-01-01\"," +
				"\"rol\":\"Usuario\"," +
				"\"departamento\":\"TI\"" +
				"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(updatedUsuario, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
		assertThat(response.getBody()).contains("33.333.333-3");
		assertThat(response.getBody()).contains("Juan");
		assertThat(response.getBody()).contains("Perez");
		assertThat(response.getBody()).contains("Gonzalez");
		assertThat(response.getBody()).contains("3323");
		assertThat(response.getBody()).contains("juan.perez@email.com");
		System.out.println("Response: " + response.getBody());
	}

	// Eliminacion de Delete
	@Test
	public void testDeleteUsuario() throws Exception {
		System.out.println("Testing the deleteUsuario method...");
		String url = "http://localhost:" + port + "/api/v1/usuarios/33.333.333-3";

		this.restTemplate.delete(url);

		// Verificar que el usuario ya no existe
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).isNull();
		System.out.println("Usuario eliminado correctamente.");
	}

}