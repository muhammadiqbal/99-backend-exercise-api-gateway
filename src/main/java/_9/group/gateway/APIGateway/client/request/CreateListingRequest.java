package _9.group.gateway.APIGateway.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateListingRequest {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("listing_type")
    private String listingType;

    @JsonProperty("price")
    private Integer price;
}
