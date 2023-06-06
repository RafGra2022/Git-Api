package com.github.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"webclient.github.url=http://localhost:1080"})
public class GithubRepositoryControllerIntegrationTest {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private WebTestClient webTestClient;
    private ClientAndServer mockServer;
	
	
    @BeforeEach
    public void setup(WebApplicationContext context) throws IOException {
        webTestClient = MockMvcWebTestClient.bindToApplicationContext(context).build();
        mockServer = ClientAndServer.startClientAndServer(1080);
        mockServer.when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/users/octocat/repos"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withHeader(Header.header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                        .withBody(loadJsonFile("repos.json")));
        mockServer.when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/repos/octocat/Hello-World/branches"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withHeader(Header.header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                        .withBody(loadJsonFile("branches-Hello-World.json")));
        mockServer.when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/repos/octocat/hello-worId/branches"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withHeader(Header.header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                        .withBody(loadJsonFile("branches-hello-worId.json")));
    }
    

    @AfterEach
    public void tearDown() {
        mockServer.stop();
    }
    
    @Test
    void githubRepositoryController_green_path_should_return_200() throws IOException {
        webTestClient.get().uri("/github/repository").header("user", "octocat")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(loadJsonFile("expected-response-200.json"));
    }
    
    @Test
    void githubRepositoryController_error_path_should_return_400() throws IOException{
        webTestClient.get().uri("/github/repository").header("user", "")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().json(loadJsonFile("bad-request-response-400.json"));
    }
    
    @Test
    void githubRepositoryController_error_path_should_return_404() throws IOException{
        webTestClient.get().uri("/github/repository").header("user", "test")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().json(loadJsonFile("not-found-response-404.json"));
    }
    
    @Test
    void githubRepositoryController_error_path_should_return_406() throws IOException{
        webTestClient.get().uri("/github/repository").header("user", "octocat")
        		.accept(MediaType.APPLICATION_XML)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_ACCEPTABLE)
                .expectBody().json(loadJsonFile("not-acceptable-response-406.json"));
    }

    private String loadJsonFile(String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:%s".formatted(filename));
        byte[] jsonData = Files.readAllBytes(resource.getFile().toPath());
        return new String(jsonData, StandardCharsets.UTF_8);
    }

}
