package _9.group.gateway.APIGateway.client.response;

import lombok.Data;

import java.util.List;

@Data
public class GetListingsResponse {
    private Boolean result;
    private List<ListingResponse> listings;
}
