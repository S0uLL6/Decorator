import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    // ─── Паттерн Decorator ───────────────────────────────────────────────────

    // Абстрактный декоратор — оборачивает любое блюдо и добавляет к нему опцию.
    // Никаких if/switch — каждый конкретный декоратор сам знает своё название и цену.
    static abstract class DishDecorator implements Dish {
        private final Dish wrapped;

        DishDecorator(Dish dish) {
            this.wrapped = dish;
        }

        // Название: «предыдущее название + наша добавка»
        @Override
        public String getName() {
            return wrapped.getName() + " +" + getModifierName();
        }

        // Цена: предыдущая цена + наша надбавка
        @Override
        public int getPrice() {
            return wrapped.getPrice() + getModifierPrice();
        }

        // Каждый декоратор реализует только эти два метода — никаких if/switch
        abstract String getModifierName();
        abstract int getModifierPrice();
    }

    // Конкретные декораторы — по одному на каждый модификатор
    static class FireSauce extends DishDecorator {
        FireSauce(Dish dish) { super(dish); }
        @Override String getModifierName()  { return "Огненный соус"; }
        @Override int    getModifierPrice() { return 40; }
    }

    static class DoubleDeer extends DishDecorator {
        DoubleDeer(Dish dish) { super(dish); }
        @Override String getModifierName()  { return "Двойная порция оленины"; }
        @Override int    getModifierPrice() { return 20; }
    }

    static class SnowBerries extends DishDecorator {
        SnowBerries(Dish dish) { super(dish); }
        @Override String getModifierName()  { return "Снежные ягоды"; }
        @Override int    getModifierPrice() { return 6; }
    }

    static class NordicFlatbread extends DishDecorator {
        NordicFlatbread(Dish dish) { super(dish); }
        @Override String getModifierName()  { return "Нордская лепёшка"; }
        @Override int    getModifierPrice() { return 7; }
    }

    // ─── GUI ─────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Гарцующая Кобыла — Заказ рагу");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 450);
            frame.setLayout(new BorderLayout(10, 10));

            // Панель с чекбоксами модификаторов
            JPanel checkPanel = new JPanel(new GridLayout(4, 1, 5, 5));
            checkPanel.setBorder(BorderFactory.createTitledBorder("Модификаторы"));

            JCheckBox cbFire      = new JCheckBox("Огненный соус (+40 септимов)");
            JCheckBox cbDeer      = new JCheckBox("Двойная порция оленины (+20 септимов)");
            JCheckBox cbBerries   = new JCheckBox("Снежные ягоды (+6 септимов)");
            JCheckBox cbFlatbread = new JCheckBox("Нордская лепёшка (+7 септимов)");

            JCheckBox[] boxes = {cbFire, cbDeer, cbBerries, cbFlatbread};

            // Блокируем четвёртый чекбокс, когда выбраны 3
            for (JCheckBox box : boxes) {
                box.addActionListener(e -> {
                    int checked = 0;
                    for (JCheckBox b : boxes) if (b.isSelected()) checked++;
                    for (JCheckBox b : boxes) {
                        if (!b.isSelected()) b.setEnabled(checked < 3);
                    }
                });
            }

            checkPanel.add(cbFire);
            checkPanel.add(cbDeer);
            checkPanel.add(cbBerries);
            checkPanel.add(cbFlatbread);

            // Кнопка заказа
            JButton orderBtn = new JButton("Оформить заказ");
            orderBtn.setFont(new Font("Arial", Font.BOLD, 14));

            // Список истории заказов
            DefaultListModel<String> historyModel = new DefaultListModel<>();
            JList<String> historyList = new JList<>(historyModel);
            JScrollPane scrollPane = new JScrollPane(historyList);
            scrollPane.setBorder(BorderFactory.createTitledBorder("Свиток заказов"));

            // Обработка кнопки: собираем декораторы и записываем заказ
            orderBtn.addActionListener(e -> {
                Dish order = new NordicStew();

                // Оборачиваем базовое блюдо нужными декораторами
                if (cbFire.isSelected())      order = new FireSauce(order);
                if (cbDeer.isSelected())      order = new DoubleDeer(order);
                if (cbBerries.isSelected())   order = new SnowBerries(order);
                if (cbFlatbread.isSelected()) order = new NordicFlatbread(order);

                String time  = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                String entry = time + "  |  " + order.getName() + "  |  " + order.getPrice() + " септимов";
                historyModel.addElement(entry);
            });

            frame.add(checkPanel, BorderLayout.NORTH);
            frame.add(orderBtn,   BorderLayout.CENTER);
            frame.add(scrollPane, BorderLayout.SOUTH);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
