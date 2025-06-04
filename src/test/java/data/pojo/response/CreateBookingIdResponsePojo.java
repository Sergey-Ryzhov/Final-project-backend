package data.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateBookingIdResponsePojo {

    private Integer bookingid;
    private CreateBookingResponsePojo booking;
}
