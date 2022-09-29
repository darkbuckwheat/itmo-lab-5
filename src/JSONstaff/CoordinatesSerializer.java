package JSONstaff;

import Classes.Coordinates;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;

public class CoordinatesSerializer implements JsonSerializer<Coordinates>
{
    @Override
    public JsonElement serialize(Coordinates cords, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject result = new JsonObject();

        result.addProperty("x", cords.getX());
        result.addProperty("y", cords.getY());

        return result;

    }
}
