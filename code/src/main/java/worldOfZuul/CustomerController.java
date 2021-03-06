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
            System.out.println("Your current customer died... ");
        }
    }

    private void fullyFed() {
        if (currentCustomer.getCurrentHp() == 10) {
            System.out.println("Customer is full :D");
            newCustomer();
        }
    }

    public void feedCustomer(Item item, Inventory inv) {

        if (item instanceof Food) {
            Food food = (Food) item;
            if (inv.removeItem(food.getName(), 1)) {
                int sat = food.getSaturation();

                currentCustomer.addHP(sat);
                System.out.println("The customer feasts on the " + food.getName() + " and gains " + food.getSaturation() + " hp!");
                currentCustomer.setTick(0);
                fullyFed();
            } else {
                System.out.println("You don't have any " + food.getName());
            }
        } else if (item instanceof Ingredient) {
            Ingredient ingredient = (Ingredient) item;
            if (ingredient.getEdible()) {
                if (inv.removeItem(ingredient.getName(), 1)) {
                    int sat = ingredient.getSaturation();
                    currentCustomer.addHP(sat);
                    System.out.println("The customer consumes the " + ingredient.getName() + " and gains " + ingredient.getSaturation() + " hp!");
                    currentCustomer.setTick(0);
                    fullyFed();
                } else {
                    System.out.println("You don't have any " + ingredient.getName());
                }

            } else {
                System.out.println("You can't eat " + ingredient.getName());
            }
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
