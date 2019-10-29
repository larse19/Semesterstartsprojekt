package worldOfZuul;

/**
 * Field
 */
public class Field extends Room {
    private static Ingredient crop;
    /**
     * States:
     *  0 Empty
     *  1 growing
     *  2 growing
     *  3 Ready to harvest
     */
    private static int state;

    // Constructor
    public Field(String description, String inCrop) {
        super(description);
        crop = new Ingredient(inCrop);
        state = 3;
    }
    // Second constructor
    public Field(String description, Ingredient inCrop) {
        super(description);
        crop = inCrop;
        state = 3;
    }

    public static Ingredient getCrop() {
        return crop;
    }

    public static void setCrop(String inCrop) {
        crop = new Ingredient(inCrop);
    }

    public static void setCrop(Ingredient inCrop) {
        crop = inCrop;
    }

    public static boolean isReadyToHarvest() {
        if (state == 3) {
            return true;
        } else {
            return false;
        }
    }

    public static Ingredient harvest() {
        state = 0;
        return getCrop();
    }

    public static void waterCrops() {
        if (state == 1 || state == 2) {
            state++;
        } else {
            System.out.println("The field cannot be watered right now.");
        }
    }

    public static void sowField(String inCrop) {
        crop = new Ingredient(inCrop);
        state = 1;
    }
    public static void sowField(Ingredient inCrop) {
        crop = inCrop;
        state = 1;
    }
}