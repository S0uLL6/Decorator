// Абстрактный декоратор — оборачивает любое блюдо и добавляет к нему опцию.
// Никаких if/switch — каждый конкретный декоратор сам знает своё название и цену.
public abstract class DishDecorator implements Dish {
    private final Dish wrapped;

    public DishDecorator(Dish dish) {
        this.wrapped = dish;
    }

    // Название: «предыдущее + добавка»
    @Override
    public String getName() {
        return wrapped.getName() + " +" + getModifierName();
    }

    // Цена: предыдущая + надбавка
    @Override
    public int getPrice() {
        return wrapped.getPrice() + getModifierPrice();
    }

    // Каждый декоратор реализует только эти два метода — никаких if/switch
    public abstract String getModifierName();
    public abstract int getModifierPrice();
}
