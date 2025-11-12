package uap.usic.siga.servicios;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uap.usic.siga.dto.UserUpdateDto;

import uap.usic.siga.entidades.Usuarios;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keno&Kemo on 03.12.2017..
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class UserUpdateDtoService {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public List<UserUpdateDto> findAll(){
        List<Usuarios> userList = userService.findAllEagerly();
        List<UserUpdateDto> userUpdateDtosList = new ArrayList<>();

        for(Usuarios user : userList){
            userUpdateDtosList.add(modelMapper.map(user, UserUpdateDto.class));
        }
        return userUpdateDtosList;
    }

    public UserUpdateDto findById(Long id){
        return modelMapper.map(userService.findByIdEagerly(id), UserUpdateDto.class);
    }


}
