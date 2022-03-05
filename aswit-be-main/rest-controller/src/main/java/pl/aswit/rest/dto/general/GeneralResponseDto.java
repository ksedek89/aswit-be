package pl.aswit.rest.dto.general;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pl.aswit.rest.enums.StatusE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GeneralResponseDto {
    private StatusE status;
    private String errorCode;
    private String errorMessage;
    private String feMessage;
}
