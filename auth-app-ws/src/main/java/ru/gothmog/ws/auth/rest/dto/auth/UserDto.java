package ru.gothmog.ws.auth.rest.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import ru.gothmog.ws.auth.rest.dto.AbstractDto;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto extends AbstractDto {
    private String username;
    private String email;
}
