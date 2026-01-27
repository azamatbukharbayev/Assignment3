package Service;

import Entities.Event;
import Entities.EventStatus;
import Entities.Seat;
import Exceptions.EventCancelled;
import Exceptions.SeatAlreadyBooked;

public void buyTicket(UUID eventId, UUID seatId, UUID customerId) {

    Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));

    if (event.getStatus() == EventStatus.CANCELLED) {
        throw new EventCancelled(eventId);
    }

    Seat seat = seatRepository.findById(seatId)
            .orElseThrow(() -> new RuntimeException("Seat not found"));

    if (seat.isBooked()) {
        throw new SeatAlreadyBooked(seatId);
    }

}
