package worldOfZuul;

/**
 * Field
 */
public class Field extends Room {

    private String crop;
    /**
     * States: 0 Empty 1 Needs water 2 growing 3 growing 4 Ready to harvest
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
        } else if(state > 0 && state <4){
            System.out.println("Not ready to harvest");
            return false;
        }
        else{
            System.out.println("Nothing is plantet here");
            return false;
        }
    }

    public void harvest(Inventory inv) {
        inv.putItem(crop, 4);
        state = 0;
        crop = null;
    }

    public void waterCrops(Inventory inv) {
        if (state == 1) {
            if (inv.removeItem("water", 1)) {
                state++;
                System.out.println("You have watered the field");
            }
        } else {
            System.out.println("The field cannot be watered right now.");
        }

    }

    public void sowField(String inCrop, Inventory inv) {
        if(inv.removeItem(inCrop, 1)){
            crop = inCrop;
            state = 1;
            System.out.println("You have sown " + inCrop);
        }
    }

    public void grow() {
        if (crop != null) {
            if (state >= 2 && state != 4) {
                state++;
            }
        }
    }
}
