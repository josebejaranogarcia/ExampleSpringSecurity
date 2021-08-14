package security.examplespringsecurity.config;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum PlayerAuthorities {

    PLAYER_READ ("player:read"),
    PLAYER_BUY ("player:buy"),
    MODERATOR_READ("moderator:read"),
    MODERATOR_WRITE  ("moderator:write"),
    ALL ("admin:all");


    private final String playerAuthority;

    PlayerAuthorities(String playerAuthority) {
        this.playerAuthority = playerAuthority;
    }

}
