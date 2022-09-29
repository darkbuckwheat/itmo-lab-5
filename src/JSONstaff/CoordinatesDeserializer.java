package JSONstaff;

import Classes.Coordinates;
import Exceptions.IncorrectFieldValueException;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {
    public Coordinates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        try {
            return new Coordinates(jsonObject.get("x").getAsLong(), jsonObject.get("y").getAsDouble());
        } catch (IncorrectFieldValueException e) {
            e.printStackTrace();
            return null;
        }
    }
}
