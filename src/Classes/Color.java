package Classes;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Enum that must be realised dy task
 */
public enum Color {
    RED("красный", "red"),
    ORANGE("оранжевый", "orange"),
    YELLOW("жёлтый", "yellow"),
    GREEN("зелёный", "green"),
    LITEBLUE("голубой", "liteblue"),
    BLUE("синий", "blue"),
    VIOLET("фиолетовый", "violet"),
    BLACK("чёрный", "black"),
    GREY("серый", "grey"),
    WHITE("белый", "white");

    private final String colorName;
    private final String engColorName;

    private Color(String colorName, String engColorName) {
        this.colorName = colorName;
        this.engColorName = engColorName;
    }

    private final static Map<String, Color> colors = Arrays.stream(Color.values())
            .collect(Collectors.toMap(k->k.colorName, v->v));
    private final static Map<String, Color> engColors = Arrays.stream(Color.values())
            .collect(Collectors.toMap(k->k.engColorName, v->v));

    /**
     * This static method takes string with color on russian or english and returns needed element
     * @param colorName string with name, that user wants to get
     * @return Enum-element with name, matches <b>colorName</b>
     */
    public static Color getColorByName(String colorName) {
        if (colors.get(colorName.toLowerCase()) != null){
            return colors.get(colorName.toLowerCase());
        }
        else if (engColors.get(colorName.toLowerCase()) != null)
        {
            return engColors.get(colorName.toLowerCase());
        }
        else {
            return null;
        }
    }

    public String toString() {
        return colorName;
    }
}
