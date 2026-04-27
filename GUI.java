import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Весь GUI — окно заказа
public class GUI extends JFrame {

    public GUI() {
        setTitle("Гарцующая Кобыла — Заказ рагу");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLayout(new BorderLayout(10, 10));

        // Панель с чекбоксами модификаторов
        JPanel checkPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        checkPanel.setBorder(BorderFactory.createTitledBorder("Модификаторы"));

        JCheckBox cbFire      = new JCheckBox("Огненный соус (+40 септимов)");
        JCheckBox cbDeer      = new JCheckBox("Двойная порция оленины (+20 септимов)");
        JCheckBox cbBerries   = new JCheckBox("Снежные ягоды (+6 септимов)");
        JCheckBox cbFlatbread = new JCheckBox("Нордская лепёшка (+7 септимов)");

        JCheckBox[] boxes = {cbFire, cbDeer, cbBerries, cbFlatbread};

        // Блокируем лишние чекбоксы, когда выбраны 3
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

        // Обработка кнопки: оборачиваем блюдо нужными декораторами и записываем заказ
        orderBtn.addActionListener(e -> {
            Dish order = new NordicStew();

            if (cbFire.isSelected())      order = new FireSauce(order);
            if (cbDeer.isSelected())      order = new DoubleDeer(order);
            if (cbBerries.isSelected())   order = new SnowBerries(order);
            if (cbFlatbread.isSelected()) order = new NordicFlatbread(order);

            String time  = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String entry = time + "  |  " + order.getName() + "  |  " + order.getPrice() + " септимов";
            historyModel.addElement(entry);
        });

        add(checkPanel, BorderLayout.NORTH);
        add(orderBtn,   BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }
}
