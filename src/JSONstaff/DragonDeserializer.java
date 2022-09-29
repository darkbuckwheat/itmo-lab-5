package JSONstaff;

import Classes.Color;
import Classes.Coordinates;
import Classes.Dragon;
import Classes.DragonCave;

import Exceptions.FieldNullException;
import Exceptions.IncorrectFieldValueException;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

public class DragonDeserializer implements JsonDeserializer<Dragon> {
    public Dragon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context){
        JsonObject jsonObject = json.getAsJsonObject();
        Dragon dragon = null;
        try {
            dragon = new Dragon(jsonObject.get("name").getAsString(),
                    context.deserialize(jsonObject.get("coordinates"), Coordinates.class),
                    jsonObject.get("age").getAsInt(),
                    jsonObject.get("wingspan").getAsFloat(),
                    jsonObject.get("speaking").getAsBoolean(),
                    Color.getColorByName(jsonObject.get("color").getAsString()),
                    context.deserialize(jsonObject.get("cave"), DragonCave.class));
        } catch (FieldNullException | IncorrectFieldValueException e) {
            e.printStackTrace();
        }
        assert dragon != null;
        dragon.setId(jsonObject.get("id").getAsLong());
        return dragon;
    }
}
