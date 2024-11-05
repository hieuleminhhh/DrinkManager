//package drinkmanage.example.drinkmanage2.Rest;
//
//import java.util.Collections;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
//
//@Configuration
//public class AuthenticationManagerProvider {
//
//    private final JwtDecoder jwtDecoder;
//
//    public AuthenticationManagerProvider(JwtDecoder jwtDecoder) {
//        this.jwtDecoder = jwtDecoder;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider()));
//    }
//    @Bean
//    public JwtAuthenticationProvider jwtAuthenticationProvider() {
//        return new JwtAuthenticationProvider(jwtDecoder);
// }
//}
