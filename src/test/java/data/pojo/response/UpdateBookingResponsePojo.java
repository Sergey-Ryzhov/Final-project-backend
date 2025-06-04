package data.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UpdateBookingResponsePojo {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private UpdateBookingdatesResponsePojo bookingdates;
    private String additionalneeds;
}
