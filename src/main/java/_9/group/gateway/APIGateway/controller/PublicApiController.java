package _9.group.gateway.APIGateway.controller;

import _9.group.gateway.APIGateway.client.ListingClient;
import _9.group.gateway.APIGateway.client.UserClient;
import _9.group.gateway.APIGateway.client.request.CreateListingRequest;
import _9.group.gateway.APIGateway.client.request.CreateUserRequest;
import _9.group.gateway.APIGateway.client.response.ListingResponse;
import _9.group.gateway.APIGateway.dto.Listing;
import _9.group.gateway.APIGateway.dto.ResponseBody;
import _9.group.gateway.APIGateway.dto.User;
import _9.group.gateway.APIGateway.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public-api")
@RequiredArgsConstructor
public class PublicApiController {
    private final UserClient userClient;
    private final ListingService listingService;
    private final ListingClient listingClient;

    @GetMapping(value = "/listings")
    public ResponseEntity<ResponseBody<List<Listing>>> listings(
            @RequestParam(required = false, name = "page_num") Integer pageNum,
            @RequestParam(required = false, name = "page_size") Integer pageSize,
            @RequestParam(required = false, name = "user_id") Long userId
    ) {
        List<Listing> listings = listingService.getListings(
                pageNum,
                pageSize,
                userId
        );
        ResponseBody<List<Listing>> response = new ResponseBody<>();
        response.setResult(true);
        response.setObject(listings);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/listings")
    public ResponseEntity<ListingResponse> createListing(@RequestBody CreateListingRequest request) {
        ListingResponse listing = listingClient.createListing(
                request.getUserId(),
                request.getListingType(),
                request.getPrice()
        ).getListing();
        return ResponseEntity.ok(listing);
    }


    @PostMapping(value = "/users")
    public ResponseEntity<ResponseBody<User>> createUser(@RequestBody CreateUserRequest request) {
        User user = userClient.registerUser(request.getName());

        ResponseBody<User> responseBody = new ResponseBody<>();
        responseBody.setResult(true);
        responseBody.setObject(user);

        return ResponseEntity.ok(responseBody);
    }


}
