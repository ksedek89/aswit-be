package pl.aswit.rest.dto.user.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pl.aswit.rest.dto.enums.StatusE;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GenericResponseDto {
    private StatusE status;
    private String message;
}
