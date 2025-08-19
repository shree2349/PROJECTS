import java.awt.*;
import java.util.*;
import javax.swing.*;

class TicketSystem {
    private int availableSeats;
    private Set<String> bookedTickets;

    public TicketSystem(int seats) {
        this.availableSeats = seats;
        this.bookedTickets = new HashSet<>();
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean bookTicket(String name) {
        if (availableSeats > 0) {
            bookedTickets.add(name);
            availableSeats--;
            return true;
        }
        return false;
    }

    public boolean cancelTicket(String name) {
        if (bookedTickets.remove(name)) {
            availableSeats++;
            return true;
        }
        return false;
    }

    public Set<String> getBookedTickets() {
        return bookedTickets;
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicketSystem system = new TicketSystem(60);

            JFrame frame = new JFrame("Ticket Booking System");
            frame.setSize(4000, 3000);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            JLabel seatLabel = new JLabel("Available Seats: " + system.getAvailableSeats());
            JButton viewButton = new JButton("View Seats");
            JButton bookButton = new JButton("Book Ticket");
            JButton cancelButton = new JButton("Cancel Ticket");
            JButton showButton = new JButton("Show All Booked Tickets");

            frame.add(seatLabel);
            frame.add(viewButton);
            frame.add(bookButton);
            frame.add(cancelButton);
            frame.add(showButton);

            // Action: View seats
            viewButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "Available Seats: " + system.getAvailableSeats());
            });

            // Action: Book ticket
            bookButton.addActionListener(e -> {
                String name = JOptionPane.showInputDialog(frame, "Enter your name:");
                if (name != null && !name.trim().isEmpty()) {
                    if (system.bookTicket(name)) {
                        JOptionPane.showMessageDialog(frame, "Ticket booked for " + name);
                        seatLabel.setText("Available Seats: " + system.getAvailableSeats());
                    } else {
                        JOptionPane.showMessageDialog(frame, "No seats available!");
                    }
                }
            });

            // Action: Cancel ticket
            cancelButton.addActionListener(e -> {
                String name = JOptionPane.showInputDialog(frame, "Enter your name to cancel:");
                if (system.cancelTicket(name)) {
                    JOptionPane.showMessageDialog(frame, "Ticket canceled for " + name);
                    seatLabel.setText("Available Seats: " + system.getAvailableSeats());
                } else {
                    JOptionPane.showMessageDialog(frame, "No booking found for " + name);
                }
            });

            // Action: Show all booked tickets
            showButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "Booked Tickets: " + system.getBookedTickets());
            });

            frame.setVisible(true);
        });
    }
}

