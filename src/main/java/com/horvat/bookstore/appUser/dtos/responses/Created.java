package com.horvat.bookstore.appUser.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Created {
    private Integer id;
    private Boolean active;
    private Boolean registered;
}
