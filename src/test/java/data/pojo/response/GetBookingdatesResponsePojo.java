package data.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GetBookingdatesResponsePojo {
    private String checkin;
    private String checkout;
}
