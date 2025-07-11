package _9.group.gateway.APIGateway;

import _9.group.gateway.APIGateway.client.response.GetListingsResponse;
import _9.group.gateway.APIGateway.client.response.ListingResponse;
import _9.group.gateway.APIGateway.dto.Listing;
import _9.group.gateway.APIGateway.dto.User;

import java.util.ArrayList;
import java.util.List;

public interface Fixture {

    default ListingResponse aExpectedListingResponse(){
        ListingResponse listingResponse = new ListingResponse();
        listingResponse.setListingType("rent");
        listingResponse.setPrice(6000);
        listingResponse.setUserId(1L);
        listingResponse.setId(1L);
        listingResponse.setCreatedAt(1475820997000000L);
        listingResponse.setUpdatedAt(1475820997000000L);

        return listingResponse;
    }

    default User aExpectedUser(){
        User user = new User();
        user.setId(1L);
        user.setName("Suresh Subramaniam");
        user.setCreatedAt(1475820997000000L);
        user.setUpdatedAt(1475820997000000L);
        return user;
    }

    default GetListingsResponse aGetListingsResponse(){
        GetListingsResponse listingResponse = new GetListingsResponse();
        List<ListingResponse> listings = new ArrayList<>();
        listings.add(aExpectedListingResponse());
        listingResponse.setListings(listings);

        return listingResponse;
    }

    default Listing aExpectedListing() {
        Listing listing = new Listing();
        listing.setId(1L);
        listing.setListingType("rent");
        listing.setPrice(6000);
        listing.setUser(aExpectedUser());
        listing.setCreatedAt(1475820997000000L);
        listing.setUpdatedAt(1475820997000000L);
        return listing;
    }
}
