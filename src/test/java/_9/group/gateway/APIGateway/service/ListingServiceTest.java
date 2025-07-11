package _9.group.gateway.APIGateway.service;

import _9.group.gateway.APIGateway.Fixture;
import _9.group.gateway.APIGateway.client.ListingClient;
import _9.group.gateway.APIGateway.client.UserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ListingServiceTest implements Fixture {

    private ListingService listingService;

    @Mock
    private ListingClient listingClient;

    @Mock
    private UserClient userClient;


    @BeforeEach
    public void setUp() {
        listingService = new ListingService(listingClient, userClient);
    }

    @Test
    void getListings_shouldReturnCorrectListings() {
        // when
        when(listingClient.getListings(any(), any(), any()))
                .thenReturn(aGetListingsResponse());

        when(userClient.getUserById(any()))
                .thenReturn(aExpectedUser());

        //then
        assertThat(listingService.getListings(any(), any(), any())).isNotEmpty();
        assertThat(listingService.getListings(any(), any(), any())).first().isEqualTo(aExpectedListing());
    }
}
