package worldOfZuul;

/**
 * Field
 */
public class Field extends Room {
    private Ingredient crop;
    /**
     * States:
     *  0 Empty
     *  1 growing
     *  2 growing
     *  3 Ready to harvest
     */
    private int state;
    // Constructor
    public Field(String description, String crop){
        super(description);
        this.crop = new Ingredient(crop);
        state = 3;
    }

    public Ingredient getCrop() {
        return crop;
    }

    public void setCrop(String crop ) {
        this.crop = new Ingredient(crop); 
    }
    public void setCrop(Ingredient crop ) {
        this.crop = crop; 
    }


    public boolean isReadyToHarvest() {
        if (state == 3) {
            return true;
        } else {
            return false;
        }
    }

    public Ingredient harvest() {
        state = 0;
        return getCrop();
    }

    public void waterCrops() {
        if (state == 1 || state == 2) {
            state++;
        } else {
            System.out.println("The field cannot be watered right now.");
        }
    }
    
    public void sowField(String crop) {
        this.crop = new Ingredient(crop);
        state = 1;
    }
    public void sowField(Ingredient crop) {
        this.crop = crop;
        state = 1;
    }
}