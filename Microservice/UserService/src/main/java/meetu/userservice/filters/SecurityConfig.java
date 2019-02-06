package meetu.userservice.filters;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

//@EnableWebSecurity
//@Configuration
//public class SecurityConfig  {
//    
//    @Configuration
//    @Order(1)
//    public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            System.out.println("Order 1 ");
//            http.csrf().disable().authorizeRequests()
//                    .antMatchers("/**").permitAll()
//                    .anyRequest().authenticated();
//        }
//    }
//}
