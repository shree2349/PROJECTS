import java.util.*;  

// This class handles all ticket booking logic
class TicketSystem {
    private int totalSeats;                 // total number of seats in the system
    private int availableSeats;             // seats currently available
    private Map<String, String> bookedTickets; // stores bookingId → seatInfo

    // ✅ Constructor: initializes the system
    public TicketSystem(int totalSeats) {
        this.totalSeats = totalSeats;      // 'this' refers to the current object
        this.availableSeats = totalSeats;  // initially, all seats are free
        this.bookedTickets = new HashMap<>(); // empty booking list
    }

    // Show how many seats are free
    public void viewAvailableSeats() {
        System.out.println("Available seats: " + availableSeats);
    }

    // Book a ticket (reduces availableSeats and adds to HashMap)
    public void bookTicket(String bookingId, String seatInfo) {
        if (availableSeats > 0) {
            bookedTickets.put(bookingId, seatInfo); // add new booking
            availableSeats--; // reduce seat count
            System.out.println("✅ Ticket booked! " + bookingId + " → " + seatInfo);
        } else {
            System.out.println("❌ Sorry, no seats available!");
        }
    }

    // Cancel a ticket (increases availableSeats and removes from HashMap)
    public void cancelTicket(String bookingId) {
        if (bookedTickets.containsKey(bookingId)) {
            String seat = bookedTickets.remove(bookingId); // remove booking
            availableSeats++; // free up a seat
            System.out.println("✅ Ticket cancelled! " + bookingId + " → " + seat);
        } else {
            System.out.println("❌ Booking ID not found!");
        }
    }

    // Show all booked tickets
    public void showBookedTickets() {
        if (bookedTickets.isEmpty()) {
            System.out.println("No tickets booked yet.");
        } else {
            System.out.println("🎟️ Booked Tickets:");
            for (Map.Entry<String, String> entry : bookedTickets.entrySet()) {
                System.out.println(entry.getKey() + " → " + entry.getValue());
            }
        }
    }
}

// Main class: runs the program (menu-driven)
public class TicketBookingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketSystem system = new TicketSystem(5); // 🎯 Example: 5 seats in total

        boolean running = true;
        while (running) {
            System.out.println("\n--- Ticket Booking Menu ---");
            System.out.println("1. View available seats");
            System.out.println("2. Book a ticket");
            System.out.println("3. Cancel a ticket");
            System.out.println("4. Show all booked tickets");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    system.viewAvailableSeats();
                    break;
                case 2:
                    System.out.print("Enter Booking ID: ");
                    String bId = sc.nextLine();
                    System.out.print("Enter Seat Info (e.g., Seat 1A): ");
                    String seat = sc.nextLine();
                    system.bookTicket(bId, seat);
                    break;
                case 3:
                    System.out.print("Enter Booking ID to cancel: ");
                    String cancelId = sc.nextLine();
                    system.cancelTicket(cancelId);
                    break;
                case 4:
                    system.showBookedTickets();
                    break;
                case 5:
                    running = false; // stop loop
                    System.out.println("👋 Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        sc.close();
    }
}
