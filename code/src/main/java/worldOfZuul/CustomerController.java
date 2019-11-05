package worldOfZuul;

public class CustomerController extends Room {

    private Customer currentCustomer;
    private Scoreboard sb;

    public CustomerController(String description) {
        super(description);
        currentCustomer = new Customer();

    }

    public void feedCustomer(Food food, Inventory inv) {

        if (inv.removeItem(food.getName(), 1)) {
            int sat = food.getSaturation();

            currentCustomer.addHP(sat);
        } else {
            System.out.println("You don't have any " + food.getName());
        }
    }

    public void feedCustomer(Ingredient ingredient, Inventory inv) {

        if (ingredient.getEdible()) {
            if (inv.removeItem(ingredient.getName(), 1)) {
                int sat = ingredient.getSaturation();
                currentCustomer.addHP(sat);
            } else {
                System.out.println("You don't have any " + ingredient.getName());
            }

        }
        else{
            System.out.println("You can't eat " + ingredient.getName());
        }
    }

    public void newCustomer() {
        sb.addPoints(currentCustomer.getGainedScore());
        currentCustomer = new Customer();
    }

}
