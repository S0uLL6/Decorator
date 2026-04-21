// Базовое блюдо — «Нордское рагу», 50 септимов
public class NordicStew implements Dish {

    @Override
    public String getName() {
        return "Рагу";
    }

    @Override
    public int getPrice() {
        return 50;
    }
}
