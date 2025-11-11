package uap.usic.siga.dto;

import lombok.Data;
import uap.usic.siga.anotacionesCliente.ValidRoleName;

/**
 * Created by Keno&Kemo on 08.12.2017..
 */
@Data
public class RoleDto {
    Long id;
    @ValidRoleName
    String name;
}
