package worldOfZuul;

public class CustomerController extends Room {

    private Customer currentCustomer;

    public CustomerController(String description) {
        super(description);
        currentCustomer = new Customer();

    }

    public int getHp() {
        return currentCustomer.getCurrentHp();
    }

    public void updateCustomer() {
        currentCustomer.damage();
        if (!currentCustomer.isAlive()) {
            int points = currentCustomer.getDamage();
            Scoreboard sb = Game.getScoreboard();
            sb.addPoints(points * (-1));
            newCustomer();
        }
    }

    private void fullyFed() {
        if (currentCustomer.getCurrentHp() == 10) {
            System.out.println("Customer is full :D");
            newCustomer();
        }
    }

    public void feedCustomer(Food food, Inventory inv) {

        if (inv.removeItem(food.getName(), 1)) {
            int sat = food.getSaturation();

            currentCustomer.addHP(sat);
            fullyFed();
        } else {
            System.out.println("You don't have any " + food.getName());
        }
    }

    public void feedCustomer(Ingredient ingredient, Inventory inv) {

        if (ingredient.getEdible()) {
            if (inv.removeItem(ingredient.getName(), 1)) {
                int sat = ingredient.getSaturation();
                currentCustomer.addHP(sat);
                fullyFed();
            } else {
                System.out.println("You don't have any " + ingredient.getName());
            }

        } else {
            System.out.println("You can't eat " + ingredient.getName());
        }
    }

    public void newCustomer() {
        if (currentCustomer.isAlive()) {
            Scoreboard sb = Game.getScoreboard();
            sb.addPoints(currentCustomer.getGainedScore());

        }
        currentCustomer = new Customer();
    }

}
