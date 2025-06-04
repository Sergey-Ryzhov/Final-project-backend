package data.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UpdateBookingPojo {

    private String firstname = "James";
    private String lastname = "Brown";
    private Integer totalprice = 111;
    private Boolean depositpaid = true;
    private UpdateBookingdatesPojo bookingdates = new UpdateBookingdatesPojo();
    private String additionalneeds = "Breakfast";
}
