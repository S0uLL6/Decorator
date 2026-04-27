// Декоратор: Нордская лепёшка (+7 септимов)
public class NordicFlatbread extends DishDecorator {
    public NordicFlatbread(Dish dish) { super(dish); }

    @Override
    public String getModifierName() { return "Нордская лепёшка"; }

    @Override
    public int getModifierPrice() { return 7; }
}
