package com.boulamared.jpython.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.boulamared.jpython.config.Constants;
import com.boulamared.jpython.web.dto.CodeRequest;
import com.boulamared.jpython.web.dto.CodeResponse;

import junit.framework.Assert;

/**
 * <h1>HttpRequestTest</h1>
 * <p>
 * class to test HttpRequests
 * </p>
 * 
 * @author BOULAMMO
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * This method is used to ping the webservice and test if the response
	 * matches the intended result
	 * 
	 * @throws Exception
	 */
	@Test
	public void pingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/" + Constants.PING_END_POINT_PATH,
				String.class)).contains("OK");
	}

	/**
	 * This method is used to test a none existing end-point
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testNoneExistingEndpoint() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/run";
		URI uri = new URI(baseUrl);
		CodeRequest codeRequest = new CodeRequest();

		HttpEntity<CodeRequest> request = new HttpEntity<>(codeRequest);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

		assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatusCodeValue());
	}

	@Test
	public void testExecuteWithEmptyResult() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/" + Constants.EXECUTE_END_POINT_PATH;
		URI uri = new URI(baseUrl);
		CodeRequest codeRequest = new CodeRequest();
		codeRequest.setCode("%python a = 0");

		HttpEntity<CodeRequest> request = new HttpEntity<CodeRequest>(codeRequest);

		ResponseEntity<CodeResponse> response = this.restTemplate.postForEntity(uri, request, CodeResponse.class);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		assertEquals("", response.getBody().getResult());
	}

	@Test
	public void testExecuteWithNotEmptyResult() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/" + Constants.EXECUTE_END_POINT_PATH;
		URI uri = new URI(baseUrl);
		CodeRequest codeRequestEmpty = new CodeRequest();
		codeRequestEmpty.setCode("%python a = 0");

		HttpEntity<CodeRequest> request = new HttpEntity<CodeRequest>(codeRequestEmpty);

		ResponseEntity<CodeResponse> responseEmpty = this.restTemplate.postForEntity(uri, request, CodeResponse.class);
		HttpHeaders headers = responseEmpty.getHeaders();

		assertNotNull(responseEmpty);
		assertNotNull(responseEmpty.getBody());
		assertEquals(HttpStatus.OK.value(), responseEmpty.getStatusCodeValue());
		assertEquals("", responseEmpty.getBody().getResult());

		CodeRequest codeRequestNotEmpty = new CodeRequest();
		codeRequestNotEmpty.setCode("%python print(a)");
		HttpEntity<CodeRequest> requestNotempty = new HttpEntity<CodeRequest>(codeRequestNotEmpty,
				prepareRequestHeader(headers));

		ResponseEntity<CodeResponse> responseNotEmpty = this.restTemplate.postForEntity(uri, requestNotempty,
				CodeResponse.class);
		assertNotNull(responseNotEmpty);
		assertNotNull(responseNotEmpty.getBody());
		assertEquals(HttpStatus.OK.value(), responseNotEmpty.getStatusCodeValue());
		assertEquals("0", responseNotEmpty.getBody().getResult());

	}

	@Test
	public void testExecuteNotSupportedInterpreter() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/" + Constants.EXECUTE_END_POINT_PATH;
		URI uri = new URI(baseUrl);
		CodeRequest codeRequestEmpty = new CodeRequest();
		codeRequestEmpty.setCode("%ruby a = 0");

		HttpEntity<CodeRequest> request = new HttpEntity<CodeRequest>(codeRequestEmpty);

		ResponseEntity<CodeResponse> responseEmpty = this.restTemplate.postForEntity(uri, request, CodeResponse.class);
		HttpHeaders headers = responseEmpty.getHeaders();

		assertNotNull(responseEmpty);
		assertNotNull(responseEmpty.getBody());
		assertEquals(HttpStatus.OK.value(), responseEmpty.getStatusCodeValue());
		assertEquals(Constants.INTERPRETER_NOT_FOUND, responseEmpty.getBody().getResult());
	}
	
	
	@Test
	public void testExecuteExpressionError() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/" + Constants.EXECUTE_END_POINT_PATH;
		URI uri = new URI(baseUrl);
		CodeRequest codeRequestEmpty = new CodeRequest();
		codeRequestEmpty.setCode("pythonprint('Hello from python')");

		HttpEntity<CodeRequest> request = new HttpEntity<CodeRequest>(codeRequestEmpty);

		ResponseEntity<CodeResponse> responseEmpty = this.restTemplate.postForEntity(uri, request, CodeResponse.class);
		HttpHeaders headers = responseEmpty.getHeaders();

		assertNotNull(responseEmpty);
		assertNotNull(responseEmpty.getBody());
		assertEquals(HttpStatus.OK.value(), responseEmpty.getStatusCodeValue());
		assertEquals(Constants.INTERPRETER_SYNTAX_ERROR, responseEmpty.getBody().getResult());
	}
	
	@Test
	public void testExecutePythonSyntaxError() throws Exception {
		final String baseUrl = "http://localhost:" + port + "/" + Constants.EXECUTE_END_POINT_PATH;
		URI uri = new URI(baseUrl);
		CodeRequest codeRequestEmpty = new CodeRequest();
		//invoking the error in the python syntax 
		codeRequestEmpty.setCode("%python print 'Hello from python')");

		HttpEntity<CodeRequest> request = new HttpEntity<CodeRequest>(codeRequestEmpty);

		ResponseEntity<CodeResponse> responseEmpty = this.restTemplate.postForEntity(uri, request, CodeResponse.class);
		HttpHeaders headers = responseEmpty.getHeaders();

		assertNotNull(responseEmpty);
		assertNotNull(responseEmpty.getBody());
		assertEquals(HttpStatus.OK.value(), responseEmpty.getStatusCodeValue());
		assertThat(responseEmpty.getBody().getResult()).asString().contains("SyntaxError");
	}
	
	

	protected String getCurrentSessionID(HttpHeaders headers) {
		return headers.get("set-cookie").stream().collect(Collectors.joining(";"));
	}

	protected HttpHeaders prepareRequestHeader(HttpHeaders headers) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Cookie", getCurrentSessionID(headers));
		return requestHeaders;
	}

}
