package data.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateBookingRequestPojo {
    private String firstname = "Jim";
    private String lastname = "Brown";
    private Integer totalprice = 111;
    private Boolean depositpaid = true;
    private CreateBookingDatesPojo bookingdates = new CreateBookingDatesPojo();
    private String additionalneeds = "Breakfast";

}
