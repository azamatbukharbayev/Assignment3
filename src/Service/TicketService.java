package Service;

import Entities.*;
import Repositories.*;
import Exceptions.EventCancelled;
import Exceptions.SeatAlreadyBooked;
import java.time.OffsetDateTime;
import java.util.UUID;

public void buyTicket(UUID eventId, UUID seatId, UUID customerId) {

    public void buyTicket(UUID eventId, UUID seatId, UUID customerId) {
        Event event = EventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getStatus() == EventStatus.CANCELLED) {
            throw new EventCancelled(eventId);
        }

        Seat seat = SeatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.isBooked()) {
            throw new SeatAlreadyBooked(seatId);
        }

        Customer customer = CustomerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        seat.setBooked(true);
        SeatRepository.update(seat);

        Ticket ticket = new Ticket(
                UUID.randomUUID(),
                eventId,
                seatId,
                customerId,
                UUID.randomUUID().toString(),
                OffsetDateTime.now());

        TicketRepository.save(ticket);
        System.out.println(
                "Ticket bought by customer: " + customer.getFirstName() + ". Ticket code: " + ticket.getCode());
    }
}
