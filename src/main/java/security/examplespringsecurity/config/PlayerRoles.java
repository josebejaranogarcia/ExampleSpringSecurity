package security.examplespringsecurity.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static security.examplespringsecurity.config.PlayerAuthorities.*;

@Getter
@AllArgsConstructor
public enum PlayerRoles {

    PLAYER(Set.of(PLAYER_READ)),
    MODERATOR (Set.of(MODERATOR_READ, MODERATOR_WRITE)),
    ADMIN(Set.of(ALL));

    private final Set<PlayerAuthorities> playerAuthorities;

/*    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getPlayerAuthorities().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPlayerAuthority()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }*/

}
