package _9.group.gateway.APIGateway.dto.serializer;

import _9.group.gateway.APIGateway.dto.ResponseBody;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ResponseBodySerializer extends JsonSerializer<ResponseBody<?>> {

    @Override
    public void serialize(ResponseBody<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeBooleanField("result", value.getResult());

        Object obj = value.getObject();
        if (obj != null) {
            String fieldName;

            if (obj instanceof Iterable<?> iterable) {
                // Get element class name if it's a list
                Object first = iterable.iterator().hasNext() ? iterable.iterator().next() : null;
                fieldName = first != null ? getFieldName(first.getClass()) + "s" : "data";
                gen.writeFieldName(fieldName);
                gen.writeObject(iterable);
            } else {
                fieldName = getFieldName(obj.getClass());
                gen.writeFieldName(fieldName);
                gen.writeObject(obj);
            }
        }
        gen.writeEndObject();
    }

    private String getFieldName(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();

        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }
}
