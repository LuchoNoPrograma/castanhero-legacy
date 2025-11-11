package uap.usic.siga.servicios.usuariosBusquedas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uap.usic.siga.dto.UserDto;

import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchResult {
    private Page<UserDto> userPage;
    private boolean numberFormatException;
}
