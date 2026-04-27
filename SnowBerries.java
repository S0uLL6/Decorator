// Декоратор: Снежные ягоды (+6 септимов)
public class SnowBerries extends DishDecorator {
    public SnowBerries(Dish dish) { super(dish); }

    @Override
    public String getModifierName() { return "Снежные ягоды"; }

    @Override
    public int getModifierPrice() { return 6; }
}
