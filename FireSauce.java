// Декоратор: Огненный соус (+40 септимов)
public class FireSauce extends DishDecorator {
    public FireSauce(Dish dish) { super(dish); }

    @Override
    public String getModifierName() { return "Огненный соус"; }

    @Override
    public int getModifierPrice() { return 40; }
}
