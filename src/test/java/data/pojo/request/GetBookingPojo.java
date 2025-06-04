package data.pojo.request;

import data.pojo.response.GetBookingdatesResponsePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GetBookingPojo {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private GetBookingdatesResponsePojo bookingdates;
    private String additionalneeds;

}

