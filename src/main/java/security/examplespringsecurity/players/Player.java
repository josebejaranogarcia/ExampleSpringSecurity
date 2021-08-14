package security.examplespringsecurity.players;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Player {
    private Long playerId;
    private String email;


    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", email='" + email + '\'' +
                '}';
    }
}
