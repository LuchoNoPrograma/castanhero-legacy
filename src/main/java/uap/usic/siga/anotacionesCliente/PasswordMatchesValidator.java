package uap.usic.siga.anotacionesCliente;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import uap.usic.siga.dto.UserDto;

/**
 * Created by Keno&Kemo on 21.10.2017..
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches passwordMatches) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserDto userDto = (UserDto) obj;
        return userDto.getPassword().equals(userDto.getMatchingPassword());

    }

}