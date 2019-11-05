package worldOfZuul;

/**
 * Field
 */
public class Field extends Room {
    private String crop;
    /**
     * States:
     *  0 Empty
     *  1 Needs water
     *  2 growing
     *  3 growing
     *  4 Ready to harvest
     */
    private int state;

    // Constructor
    public Field(String description, String inCrop) {
        super(description);
        crop = inCrop;
        state = 4;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String inCrop) {
        crop = inCrop;
    }

    public boolean isReadyToHarvest() {
        if (state == 4) {
            return true;
        } else {
            return false;
        }
    }

    public String harvest() {
        state = 0;
        return getCrop();
    }

    public void waterCrops() {
        if (state == 1) {
            state++;
        } else {
            System.out.println("The field cannot be watered right now.");
        }
    }

    public void sowField(String inCrop) {
        crop = inCrop;
        state = 1;
    }

    public void grow(){
        if(state >= 2 && state != 4){
            state++;
        }
    }
}