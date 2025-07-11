package _9.group.gateway.APIGateway.client;

import _9.group.gateway.APIGateway.client.response.UserResponse;
import _9.group.gateway.APIGateway.dto.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.BodyInserters.fromFormData;

@Component
public class UserClient {
    private WebClient webClient;
    private String baseUrl = "http://localhost:8081";

    public UserClient() {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public UserClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public User registerUser(String name) {
        MultipartBodyBuilder requestBodyBuilder = new MultipartBodyBuilder();
        requestBodyBuilder.part("name", name);

        UserResponse userResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/users").build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(fromFormData("name", name))
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (userResponse == null) {
            return null;
        }

        return userResponse.getUser();
    }

    public User getUserById(String id) {
        String path = "/users/" + id;
        UserResponse userResponse = webClient.get().uri(
                uriBuilder -> uriBuilder
                        .path(path)
                        .build()
                )
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (userResponse == null) {
            return null;
        }

        return userResponse.getUser();
    }
}
