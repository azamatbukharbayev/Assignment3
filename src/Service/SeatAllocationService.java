package Service;

import Entities.Seat;
import Exceptions.SeatAlreadyBooked;
import Repositories.SeatRepository;
import java.util.UUID;


public class SeatAllocationService {
    public void reserveSeat(UUID seatId) {
        Seat seat = SeatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found: " + seatId));

        if (seat.isBooked()) {
            throw new SeatAlreadyBooked(seatId);
        }

        seat.setBooked(true);
        SeatRepository.update(seat);
        System.out.println("Seat booked: " + seatId);
    }
}
