package one.digitalinnovation.personalAPI.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageResponse {
    private String message;
}
