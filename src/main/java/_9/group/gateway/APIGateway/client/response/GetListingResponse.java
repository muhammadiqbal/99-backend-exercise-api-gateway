package _9.group.gateway.APIGateway.client.response;

import lombok.Data;

@Data
public class GetListingResponse {
    private Boolean result;
    private ListingResponse listing;
}
