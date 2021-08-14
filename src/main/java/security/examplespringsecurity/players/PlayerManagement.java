package security.examplespringsecurity.players;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/")
public class PlayerManagement {

    private static final List<Player> playersList = List.of(
            new Player(1L, "nemo@gamil.com"),
            new Player(2L, "azazel@gamil.com"),
            new Player(3L, "canary@gamil.com"));


    @GetMapping("players")
   // @PreAuthorize("hasAuthority('admin:all')")
    public List<Player> getPlayersList() {
        System.out.println(">>>>>>> Sending players list");
        return playersList;
    }

    @PostMapping("players")
  //  @PreAuthorize("hasAuthorities('player:write', 'admin:all')")
    public void registerPlayer(@RequestBody Player player) {
        System.out.println(">>>>>> Creating a new player");
        System.out.println(player);
    }

    @PutMapping(path = "/players/{playerId}")
 //   @PreAuthorize("hasAuthorities('player:write', 'admin:all')")
    public void updatePlayer(@PathVariable("playerId") Integer playerId,
                              @RequestBody Player player) {
        System.out.println(">>>>>>>>>> Updating player");
      //  System.out.println(String.format("%s %s", playerId, player));
    }

    @DeleteMapping(path = "players/{playerId}")
    @PreAuthorize("hasAuthorities('player:write', 'admin:all')")
    public void deletePlayer(@PathVariable("playerId") Integer playerId) {
        System.out.println(">>>>>>>>>>>> Deleting player with id: "+ playerId);
    }
}
