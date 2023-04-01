package co.edu.uptc.views.Globals;

import java.awt.*;

public class ColorPalette {
    /**
     * The list of color schemes to choose from.
     */
    public static enum SchemeEnum {
        BASIC, DARK, METALLIC
    }

    /**
     * The list of color descriptions to choose from.
     */
    public static enum ColorEnum {
        LIGHT_RED(255, 0, 0), RED(192, 0, 0), DARK_RED(128, 0, 0),
        LIGHT_GREEN(0, 255, 0), GREEN(0, 192, 0), DARK_GREEN(100, 128, 0),

        LIGHT_BLUE_N2(210, 237, 238),
        LIGHT_BLUE_N1(228, 241, 241),
        LIGHT_BLUE(0, 0, 255),
        BLUE(0, 0, 192),
        DARK_BLUE(0, 0, 128),





        LIGHT_ORANGE_N5(253, 246, 242),
        LIGHT_ORANGE_N4(246, 224, 210),

        LIGHT_ORANGE_N3(252, 205, 176),
        LIGHT_ORANGE_N2(248, 172, 124),
        LIGHT_ORANGE_N1(245, 132, 60),
        LIGHT_ORANGE(255, 102, 0),
        ORANGE(255, 100, 0),
        DARK_ORANGE(201, 79, 0),
        DARK_ORANGE_N1(158, 62, 0),

        LIGHT_YELLOW_N2(255, 255, 166),
        LIGHT_YELLOW_N1(255, 255, 137),
        LIGHT_YELLOW(255, 255, 115),
        YELLOW(255, 255, 0),
        DARK_YELLOW(200, 200, 0),
        DARK_YELLOW_N1(170, 170, 0),
        DARK_YELLOW_N2(140, 140, 0),



        LIGHT_PURPLE(136, 0, 182), PURPLE(102, 0, 153), DARK_PURPLE(78, 0, 124);



        private int red;
        private int green;
        private int blue;


        private ColorEnum(int r, int g, int b) {
            this.red = r;
            this.green = g;
            this.blue = b;

        }

        public final Color getColor() {
            // WANT TO RETURN A COLOR BASED ON currentScheme

            return new Color(red, green, blue);
        }
    }

    private static SchemeEnum currentScheme = SchemeEnum.BASIC;


    private ColorPalette() {
    }


    public static SchemeEnum getCurrentScheme() {
        return currentScheme;
    }


    public static void setCurrentScheme(SchemeEnum cp) {
        currentScheme = cp;
    }

}