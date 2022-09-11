package ru.gothmog.ws.auth.rest.mapper.auth;

import ru.gothmog.ws.auth.core.model.auth.User;
import ru.gothmog.ws.auth.rest.dto.auth.UserDto;
import ru.gothmog.ws.auth.rest.mapper.AbstractMapper;
import ru.gothmog.ws.auth.rest.mapper.MapperConverter;

@MapperConverter
public class UserMapper extends AbstractMapper<User, UserDto> {

    public UserMapper(){
        super(User.class , UserDto.class);
    }
}
