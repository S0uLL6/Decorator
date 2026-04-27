// Декоратор: Двойная порция оленины (+20 септимов)
public class DoubleDeer extends DishDecorator {
    public DoubleDeer(Dish dish) { super(dish); }

    @Override
    public String getModifierName() { return "Двойная порция оленины"; }

    @Override
    public int getModifierPrice() { return 20; }
}
