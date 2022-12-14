package ru.gothmog.ws.auth.core.model.files;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ImportData {
    private Long id;
    private String idLocality;
    private String idKeyInformName;
    private BigDecimal valueAll;
    private String reportingPeriod;
    private String createDate;
}
