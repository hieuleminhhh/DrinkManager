//package drinkmanage.example.drinkmanage2.Rest;
//
//import java.security.interfaces.RSAPublicKey;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//
//@Configuration
//public class JwtConfig {
//    
//    @Value("${your.jwt.publicKey}")
//    private RSAPublicKey publicKey;
//    
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withPublicKey(publicKey).build();
//    }
//}
