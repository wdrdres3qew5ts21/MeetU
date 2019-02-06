package meetu.userservice.filters;

import meetu.service.exceptions.BadRequestException;
//
//public class JWTAuthenFilter extends FilterSecurityInterceptor {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try {
//            TokenAuthenticationService.validateJWTAuthentication((HttpServletRequest) request);
//            HttpServletRequest httpRequest = (HttpServletRequest) request;
//            chain.doFilter(request, response);
//        } catch (io.jsonwebtoken.SignatureException signatureException) {
//            throw new BadRequestException("JWT Token has been change we dont trust your token !");
//        } catch (io.jsonwebtoken.ExpiredJwtException signExpiredJwtException) {
//            throw new BadRequestException("JWT Token Is Already Timeout Please Login Again !");
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//        super.destroy(); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//        super.init(arg0); //To change body of generated methods, choose Tools | Templates.
//    }
//
//}
