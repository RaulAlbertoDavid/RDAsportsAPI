package com.rdacompany.RDAsportsAPI.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDto {

    private int customerId;
    private int sessionId;
}
