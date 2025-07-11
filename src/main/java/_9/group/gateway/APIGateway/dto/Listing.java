package _9.group.gateway.APIGateway.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Listing {
    private Long id;
    private String listingType;
    private Integer price;
    private Long createdAt;
    private Long updatedAt;

    @Nullable
    private User user;
}
