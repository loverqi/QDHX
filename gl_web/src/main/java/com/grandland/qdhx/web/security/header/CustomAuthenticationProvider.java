//package com.grandland.qdhx.web.security.header;
//
//import javax.security.auth.login.CredentialExpiredException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
///**
// * 自定义验证程序
// * @author hongxf
// * @since 2017-03-20 14:28
// */
//@Component
//public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
//
//    @Override
//    @Autowired
//    public void setUserDetailsService(UserDetailsService userDetailsService) {
//        // TODO 自动生成的方法存根
//        super.setUserDetailsService(userDetailsService);
//    }
//
//    @Override
//    public Authentication authenticate(Authentication auth) throws AuthenticationException {
//
//        Authentication authResult = null;
//
//        try {
//            authResult = super.authenticate(auth);
//        }
//        catch (UsernameNotFoundException e) {
//        }
//        catch (BadCredentialsException e) {
//        }
////        catch (CredentialExpiredException e) {
//        }
//        catch (LockedException e) {
//        }
//        return authResult;
//
//        
//        
////        用户名不存在:UsernameNotFoundException;
////        密码错误:BadCredentialException;
////        帐户被锁:LockedException;
////        帐户未启动:DisabledException;
////        密码过期:CredentialExpiredException;等等! 
//    }
//
//}
