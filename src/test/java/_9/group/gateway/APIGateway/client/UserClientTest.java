package _9.group.gateway.APIGateway.client;

import _9.group.gateway.APIGateway.Fixture;
import _9.group.gateway.APIGateway.dto.User;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserClientTest implements Fixture {
    public static MockWebServer mockBackEnd;
    public static UserClient userClient;

    private final String GET_USER_BY_ID_RESPONSE = """
        {
            "result": true,
            "user": {
                "id": 1,
                "name": "Suresh Subramaniam",
                "created_at": 1475820997000000,
                "updated_at": 1475820997000000
            }
        }
        """;

    private final String CREATE_USER_RESPONSE = """
        {
             "result": true,
             "user": {
                 "id": 1,
                 "name": "Suresh Subramaniam",
                 "created_at": 1475820997000000,
                 "updated_at": 1475820997000000
             }
        }
        """;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
        int port = mockBackEnd.getPort();
        userClient = new UserClient("http://localhost:" + port);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void getUserById_shouldReturnCorrectUser() {
        // Given
        mockBackEnd.enqueue(new MockResponse()
                .setBody(GET_USER_BY_ID_RESPONSE)
                .addHeader("Content-Type", "application/json"));

        //When
        User response = userClient.getUserById(String.valueOf(1));

        //Then
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(aExpectedUser());
    }

    @Test
    void createUser_shouldReturnCorrectUser() {
        // Given
        mockBackEnd.enqueue(new MockResponse()
                .setBody(CREATE_USER_RESPONSE)
                .addHeader("Content-Type", "application/json"));

        //When
        User response = userClient.registerUser("Suresh Subramaniam");

        //Then
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(aExpectedUser());
    }
}
