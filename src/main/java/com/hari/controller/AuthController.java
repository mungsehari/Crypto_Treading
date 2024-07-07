package com.hari.controller;

import com.hari.config.JwtProvider;
import com.hari.model.TwoFactorOtp;
import com.hari.model.User;
import com.hari.repository.UserRepository;
import com.hari.response.AuthResponse;
import com.hari.service.Impl.CustomeUserDetailsImpl;
import com.hari.service.Impl.EmailService;
import com.hari.service.TwoFactorOtpService;
import com.hari.service.WatchlistService;
import com.hari.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomeUserDetailsImpl customeUserDetails;

    @Autowired
    private TwoFactorOtpService twoFactorOtpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private WatchlistService watchlistService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody  User user) throws Exception {

        User isEmailExist =userRepository.findByEmail(user.getEmail());
        if (isEmailExist!=null){
            throw  new Exception("email is already used whit another account !");
        }
        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFullName(user.getFullName());
        User savedUser=userRepository.save(newUser);

        watchlistService.createWatchlist(savedUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String Jwt= JwtProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse();
        res.setJwt(Jwt);
        res.setStatus(true);
        res.setMessage("register success!");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody  User user) throws Exception {
        String userName=user.getEmail();
        String password=user.getPassword();

        Authentication authentication = authenticate(userName,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String Jwt= JwtProvider.generateToken(authentication);
        User authUser=userRepository.findByEmail(userName);
        if (user.getTwoFactorAuth().isEnabled()){
            AuthResponse res=new AuthResponse();
            res.setMessage("Two Factor Auth is enabled");
            res.setTwoFactorAuthEnabled(true);
            String otp= OtpUtils.generateOtp();
            TwoFactorOtp oldTwoFactorOtp=twoFactorOtpService.findByUser(authUser.getId());
            if (oldTwoFactorOtp!=null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOtp);
            }
            TwoFactorOtp newTwoFactorOtp=twoFactorOtpService.createTwoFactorOtp(authUser,otp,Jwt);
            emailService.sendVerificationOtpEmail(userName,otp);

            res.setSession(newTwoFactorOtp.getId());
            return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
        }
        AuthResponse res=new AuthResponse();
        res.setJwt(Jwt);
        res.setStatus(true);
        res.setMessage("login success!");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String userName, String password) {

        UserDetails userDetails=customeUserDetails.loadUserByUsername(userName);
        if (userDetails==null){
            throw new BadCredentialsException("Invalid Username");

        }
        if(!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


    @PostMapping("/two-factor/otp/{otp}")
    public ResponseEntity<AuthResponse> verifySignInOtp(@PathVariable String  otp, @RequestParam String  id) throws Exception {
        TwoFactorOtp twoFactorOtp=twoFactorOtpService.findById(id);
        if (twoFactorOtpService.verifyTwoFactorOtp(twoFactorOtp,otp)){
            AuthResponse res=new AuthResponse();
            res.setMessage("Two Factor Authentication is verified");
            res.setTwoFactorAuthEnabled(true);
            res.setJwt(twoFactorOtp.getJwt());
            return new ResponseEntity<>(res,HttpStatus.OK);


        }

       throw new Exception("invalid otp");
    }

}
