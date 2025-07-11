package _9.group.gateway.APIGateway.dto.mapper;

import _9.group.gateway.APIGateway.client.response.ListingResponse;
import _9.group.gateway.APIGateway.dto.Listing;
import _9.group.gateway.APIGateway.dto.User;
import org.springframework.stereotype.Component;

@Component
public class ListingMapper {

    public static Listing map(ListingResponse listingResponse, User user) {
        Listing listing = new Listing();

        listing.setId(listingResponse.getId());
        listing.setListingType(listingResponse.getListingType());
        listing.setPrice(listingResponse.getPrice());
        listing.setCreatedAt(listingResponse.getCreatedAt());
        listing.setUpdatedAt(listingResponse.getUpdatedAt());
        listing.setUser(user);

        return listing;
    }
}
