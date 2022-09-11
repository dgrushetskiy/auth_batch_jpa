package ru.gothmog.ws.auth.rest.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ImportDataResponse {
    private String idLocality;
    private String idKeyInformName;
    private BigDecimal valueAll;
    private String reportingPeriod;
    private LocalDate createDate;
}
