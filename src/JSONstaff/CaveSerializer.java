package JSONstaff;

import Classes.DragonCave;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;

public class CaveSerializer implements JsonSerializer<DragonCave>
{
    @Override
    public JsonElement serialize(DragonCave cave, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject result = new JsonObject();
        result.addProperty("depth", cave.getDepth());
        return result;
    }
}