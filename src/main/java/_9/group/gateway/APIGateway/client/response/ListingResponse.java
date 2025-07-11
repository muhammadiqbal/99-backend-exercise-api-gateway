package _9.group.gateway.APIGateway.client.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ListingResponse {
    private Long id;
    private Long userId;
    private Integer price;
    private String listingType;
    private Long createdAt;
    private Long updatedAt;
}
