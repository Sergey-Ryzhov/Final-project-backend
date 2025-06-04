package data.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateBookingDatesPojo {

    private String checkin = "2018-01-01";
    private String checkout = "2019-01-01";
}
