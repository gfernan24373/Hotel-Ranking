package api_gateway.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Builder
public class AuthDTO {

    private String username;
    private String password;
}
