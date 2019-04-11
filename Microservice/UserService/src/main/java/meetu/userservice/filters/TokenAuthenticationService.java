package meetu.userservice.filters;

import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.UnsupportedEncodingException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import meetu.service.exceptions.BadRequestException;
import static meetu.userservice.filters.GlobalValue.EXPIRATION_TIME;
import static meetu.userservice.filters.GlobalValue.secretKey;
import meetu.userservice.model.User;

@Service
public class TokenAuthenticationService {

    public String createTokenUser(User user) {
        Date now = new Date();
        HashMap<String, Object> userJson = new HashMap<>();
        userJson.put("userId", user.getUserId());
        userJson.put("userImg", user.getUserImage());
        userJson.put("userName", user.getUsername());
        userJson.put("role", user.getRole());
        userJson.put("email", user.getEmail());
        userJson.put("firstName", user.getFirstName());
        userJson.put("lastName", user.getLastName());
        userJson.put("province", user.getProvince());
        userJson.put("country", user.getCountry());
        userJson.put("gender", user.getGender());
        
        String token = Jwts.builder()
                .claim("user", userJson)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                //.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
        return token;
    }

    public static Claims validateJWTAuthentication(HttpServletRequest request) {
        System.out.println(secretKey);
        String token = request.getHeader("Authorization");
        try {
            if (token != null) {
                try {
                    Claims user = (Claims) (Jwts.parser().setSigningKey(Base64.getEncoder()
                            .encodeToString(secretKey.getBytes("UTF-8")))
                            .parseClaimsJws(token.replace("Bearer ", ""))
                            .getBody());
                    return user;
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(TokenAuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                throw new BadRequestException("Authorization Header is not found !");
            }
        } catch (io.jsonwebtoken.SignatureException signatureException) {
            throw new BadRequestException("JWT Token has been change we dont trust your token !");
        } catch (io.jsonwebtoken.ExpiredJwtException signExpiredJwtException) {
            throw new BadRequestException("JWT Token Is Already Timeout Please Login Again !");
        }
        return null;
    }

    public static void validateIsUserRoleTeacher(Claims user) {
        user.get("user", HashMap.class);
        HashMap userDetail = user.get("user", HashMap.class);
        System.out.println("user : " + userDetail.get("role"));
        if (!userDetail.get("role").toString().equals("teacher")) {
            throw new BadRequestException("This user is not Teacher so not have enough permission !");
        }
    }

}

