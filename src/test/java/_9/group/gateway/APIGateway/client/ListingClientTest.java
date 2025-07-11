package _9.group.gateway.APIGateway.client;

import _9.group.gateway.APIGateway.Fixture;
import _9.group.gateway.APIGateway.client.response.GetListingResponse;
import _9.group.gateway.APIGateway.client.response.GetListingsResponse;
import _9.group.gateway.APIGateway.client.response.ListingResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ListingClientTest implements Fixture {

    public static MockWebServer mockBackEnd;
    public static ListingClient listingClient;

    private final String CREATE_LISTING_RESPONSE = """
            {
            "result": true,
            "listing": {
                "id": 1,
                "user_id": 1,
                "listing_type": "rent",
                "price": 6000,
                "created_at": 1475820997000000,
                "updated_at": 1475820997000000
            }
        }
        """;

    private final String GET_LISTINGS_RESPONSE = """
        {
            "result": true,
            "listings": [
                {
                    "id": 1,
                    "user_id": 1,
                    "listing_type": "rent",
                    "price": 6000,
                    "created_at": 1475820997000000,
                    "updated_at": 1475820997000000
                }
            ]
        }
        """;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
        int port = mockBackEnd.getPort();
        listingClient = new ListingClient("http://localhost:" + port);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void testGetListing_shouldReturnGetListingsResonse() {


        // Given
        mockBackEnd.enqueue(new MockResponse()
                .setBody(GET_LISTINGS_RESPONSE)
                .addHeader("Content-Type", "application/json"));

        //When
        GetListingsResponse response = listingClient.getListings(null, null, null);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getResult()).isTrue();
        assertThat(response.getListings().size()).isEqualTo(1);
        assertThat(response.getListings()).first().isEqualTo(aExpectedListingResponse());
    }

    @Test
    void testCreateListing_shouldReturnGetListingResonse() {
        // Given
        mockBackEnd.enqueue(new MockResponse()
                .setBody(CREATE_LISTING_RESPONSE)
                .addHeader("Content-Type", "application/json"));

        //When
        GetListingResponse response = listingClient.createListing(1L, "rent", 6000);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getResult()).isTrue();
        assertThat(response.getListing()).isEqualTo(aExpectedListingResponse());

    }
}
