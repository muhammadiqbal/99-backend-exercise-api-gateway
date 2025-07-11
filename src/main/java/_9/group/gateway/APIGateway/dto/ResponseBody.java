package _9.group.gateway.APIGateway.dto;


import _9.group.gateway.APIGateway.dto.serializer.ResponseBodySerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonSerialize(using = ResponseBodySerializer.class)
public class ResponseBody<T> {
    private Boolean result = true;
    private T object;
}
