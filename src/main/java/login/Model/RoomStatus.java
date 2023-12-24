package login.Model;

import lombok.*;


@Getter
@RequiredArgsConstructor
public enum RoomStatus {
    DISPONIBLE("Disponible"),
    NON_DISPONIBLE("Non Disponible");

    private final String status;


}
