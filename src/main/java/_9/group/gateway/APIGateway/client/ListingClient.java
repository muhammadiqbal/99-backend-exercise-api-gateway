package _9.group.gateway.APIGateway.client;

import _9.group.gateway.APIGateway.client.response.GetListingResponse;
import _9.group.gateway.APIGateway.client.response.GetListingsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.web.reactive.function.BodyInserters.fromFormData;

@Component
public class ListingClient {
    private WebClient webClient;
    private String baseUrl = "http://localhost:6000";

    public ListingClient() {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public ListingClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public GetListingsResponse getListings(
            Integer pageNum,
            Integer pageSize,
            Long userId
    ) {
        return webClient.get()
                .uri(uriBuilder -> {
                     uriBuilder.path("/listings");

                     if (pageNum != null) { uriBuilder.queryParam("page", pageNum); }
                     if (pageSize != null) { uriBuilder.queryParam("page", pageSize); }
                     if (userId != null) { uriBuilder.queryParam("page", userId); }

                     return uriBuilder.build();
                })
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .retrieve()
                .bodyToMono(GetListingsResponse.class)
                .block();
    }

    public GetListingResponse createListing(
            Long userId,
            String listingType,
            Integer price
    ) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/listings").build())
                .body(
                        fromFormData("user_id", String.valueOf(userId))
                                .with("listing_type", listingType)
                                .with("price", String.valueOf(price))
                )
                .retrieve()
                .bodyToMono(GetListingResponse.class)
                .block();
    }
}
