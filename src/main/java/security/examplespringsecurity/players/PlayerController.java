package security.examplespringsecurity.players;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {


    private static final List<Player> playersList = List.of(
            new Player(1L, "nemo@gamil.com"),
            new Player(2L, "azazel@gamil.com"),
            new Player(3L, "canary@gamil.com"));


    @GetMapping(path = "{playerId}")
    public Player getPlayer(@PathVariable("playerId") Long playerId) {
        return playersList.stream()
                .filter(player -> playerId.equals(player.getPlayerId()))
                .findFirst()
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("Player %d is not present", playerId) ) );
    }


}
