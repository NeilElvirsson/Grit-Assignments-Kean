import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.*;

// Klass som visar en dag i kalendern
public class DayPanel extends JPanel {
    //datumet som DayPanel representerar
    private LocalDate date;
    // TextArea för att visa events
    private JTextArea eventHash;
    // HashMap som sparar info om events för respektive dag
    private static HashMap<LocalDate, ArrayList<String>> events = new HashMap<>();

    // konstruktor för DayPanel
    public DayPanel(LocalDate date) {
        this.date = date;
        setLayout(new BorderLayout());
        // Skapar en label som visar vilken dag på veckan det är och dess datum
        String dayAndDate = String.format("%s, %d %s",
                date.getDayOfWeek(),
                date.getDayOfMonth(),
                date.getMonth());
        JLabel dayLabel = new JLabel(dayAndDate);
        add(dayLabel, BorderLayout.NORTH);

        // skapar platsen där eventen kommer visas
        eventArea();
        // knapparna för att lägga till eller ta bort events
        buttons ();
    }

    // Metod som skapar platsen att ha olika events
    private void eventArea() {
        eventHash = new JTextArea(10, 20);
        //gör att det inte kan ''editas''
        eventHash.setEditable(false);

        // Gör fonten/texten större
        Font currentFont = eventHash.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), 16);
        eventHash.setFont(newFont);

        // laddar events för det specifika datumet
        ArrayList<String> existingEvents = events.getOrDefault(date, new ArrayList<>());
        for (String event : existingEvents) {
            eventHash.append(event + "\n");
        }

        // skapar en JScrollPane och lägger in i eventHash
        JScrollPane scrollPane = new JScrollPane(eventHash);
        add(scrollPane, BorderLayout.CENTER);
    }


    // Metod för att skapa knapparna för att påverka sina events
    private void buttons () {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Event");
        JButton deleteButton = new JButton("Delete Event");

        // lägger till actionlisterners i knapparna
        addButton.addActionListener(this::addEvent);
        deleteButton.addActionListener(e -> deleteEvent());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Metod för att lägga till nya event
    private void addEvent(ActionEvent e) {
        // ber användaren skriva ett event
        String event = JOptionPane.showInputDialog("Enter Event");
        // kollar ifall inputet är valid alltså inte null eller bara tom text - trim tar bort tom text innan och efter strängen
        if (event != null && !event.trim().isEmpty()) {
            // lägger till eventet i hashmappen och uppdaterar event area - ifall det är tomt när du vill lägga till något skapas ny arraylist
            events.computeIfAbsent(date, k -> new ArrayList<>()).add(event);
            eventHash.append(event + "\n"); // lägger till det på slutet
        }
    }

    // metod för att ta bort ett event
    private void deleteEvent() {
        // hämtar event för respektive dag
        ArrayList<String> dateEvents = events.get(date);
        //  ifall dataEvents är null
        if (dateEvents == null) {
            JOptionPane.showMessageDialog(this, "No events to delete for " + date, "Delete Event", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // kollar ifall dateEvents är tom
        if (dateEvents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No events to delete for " + date, "Delete Event", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // konverterar eventlistan till en array som kan visas
        String[] eventsArray = new String[dateEvents.size()];
        eventsArray = dateEvents.toArray(eventsArray);

        // låter användaren välja ett event som ska raderas
        String eventToDelete = (String) JOptionPane.showInputDialog(this, "Select an event to delete:", "Delete Event",
                JOptionPane.QUESTION_MESSAGE, null, eventsArray, eventsArray[0]);

        // ifall ett event har valts, ta bort det och uppdatera event area
        if (eventToDelete != null) {
            dateEvents.remove(eventToDelete);
            updateEventArea();
        }
    }

    // metod för att uppdatera vad som visas i event area
    private void updateEventArea() {
        // rensar event area
        eventHash.setText("");
        //hämtar events för det specifika datumet
        ArrayList<String> dateEvents = events.getOrDefault(date, new ArrayList<>());
        //loopar genom varje event och lägger till det i event area
        for (String event : dateEvents) {
            eventHash.append(event + "\n");
        }
    }
}
