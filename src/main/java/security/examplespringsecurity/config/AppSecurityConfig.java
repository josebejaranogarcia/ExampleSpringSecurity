package security.examplespringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static security.examplespringsecurity.config.PlayerAuthorities.*;
import static security.examplespringsecurity.config.PlayerRoles.PLAYER;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    public final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // white list of matches
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/players/*").hasRole(PLAYER.name())
                .antMatchers(HttpMethod.GET, "/management/players/**")
                    .hasAnyAuthority(ALL.getPlayerAuthority(), MODERATOR_READ.getPlayerAuthority())
                .antMatchers(HttpMethod.DELETE, "/management/players/**")
                    .hasAnyAuthority(ALL.getPlayerAuthority(), MODERATOR_WRITE.getPlayerAuthority())
                .anyRequest().authenticated().and().httpBasic();

        //.hasAnyRole(ADMIN.name(), MODERATOR.name())
        //.hasRole(ADMIN.name())

    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        UserDetails nemoUser = User.builder()
                .username("nemo")
                .password(passwordEncoder.encode("pass"))
//                .roles("PLAYER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
//                .roles("ADMIN")
                .build();

        UserDetails azazel = User.builder()
                .username("azazel")
                .password(passwordEncoder.encode("1234"))
//                .roles("MODERATOR")
                .build();

        return new InMemoryUserDetailsManager(nemoUser, admin, azazel);
    }


/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("nemo")
                .password(passwordEncoder.encode("pass"))
                .roles("PLAYER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN");
    }*/
}
