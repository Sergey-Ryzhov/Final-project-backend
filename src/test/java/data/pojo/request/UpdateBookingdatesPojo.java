package data.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UpdateBookingdatesPojo {
    private String checkin = "2018-02-02";
    private String checkout = "2019-02-02";
}
