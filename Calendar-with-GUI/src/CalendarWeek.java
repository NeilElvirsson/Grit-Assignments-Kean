import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.temporal.TemporalAdjusters;

public class CalendarWeek extends JFrame {
    // Sparar dagens daturm
    private LocalDate today = LocalDate.now();
    // Startdatumet för veckan
    private LocalDate startOfWeek;
    // Container panel för daypaneler
    private JPanel weekPanel;

    // konstruktor för calendarWeek klass
    public CalendarWeek() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // panelen för navigationsknapparna
        navigationPanel();
        // skapar paneler för varje dag i veckan
        weekPanels();
        // justerar frame size
        pack();
    }

    // Metod för att skapa paneler för varje dag i veckan
    private void weekPanels() {
        weekPanel = new JPanel(new GridLayout(1, 7));
        // sätter starten på veckan som måndag
        startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        // Updaterar panelerna för att visa rätt vecka
        updateWeekPanels();
        add(weekPanel, BorderLayout.CENTER);
    }

    // Metod för att uppdatera varje dagspanel för veckan
    private void updateWeekPanels() {
        // tar bort allt från containern
        weekPanel.removeAll();
        // startar från starten av veckan (måndag) och upprepar för varje dag
        for (int i = 0; i < 7; i++) {
            // kalkylerar datumet för varje dagspanel
            LocalDate date = startOfWeek.plusDays(i);
            // skapar panel för den specifika dagen
            DayPanel dayPanel = new DayPanel(date);
            // Dagens daturm är grönt, lördag och söndag är rött
            if (date.equals(LocalDate.now())) {
                dayPanel.setBackground(Color.GREEN);
            } else if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                dayPanel.setBackground(Color.RED);
            } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                dayPanel.setBackground(Color.RED);
            }
            // lägger till dayPanel till weekPanel containern
            weekPanel.add(dayPanel);
        }
        // uppdaterar containern för att visa de uppdaterade paneler
        weekPanel.revalidate(); //kalkylerar om innehållet
        weekPanel.repaint(); // metod för att uppdatera ''utseendet'' alltså det man ser i GUIen
    }

    // Metod som innehåller navigationspanelen med knapparna för gå fram och tillbaka i veckorna
    private void navigationPanel() {
        JPanel navigationPanel = new JPanel(new FlowLayout());
        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");
        // actionlisteners för gå framåt eller tillbaka i veckorna
        prevButton.addActionListener(e -> weekNavigation(-1));
        nextButton.addActionListener(e -> weekNavigation(1));
        navigationPanel.add(prevButton);
        navigationPanel.add(nextButton);
        add(navigationPanel, BorderLayout.NORTH);
    }

    // Metod för att navigera i veckorna
    private void weekNavigation(int weeks) {
        startOfWeek = startOfWeek.plusWeeks(weeks);
        // uppdaterar veckopanelerna så rätt vecka visar
        updateWeekPanels();
    }
}
