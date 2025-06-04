package data.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor

public class BookAuthorizationPojo {
    private String username = "admin";
    private String password = "password123";

}
