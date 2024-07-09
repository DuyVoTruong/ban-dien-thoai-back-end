package com.example.bandienthoai.controller;

import com.example.bandienthoai.exception.CustomException;
import com.example.bandienthoai.utils.Role;
import com.example.bandienthoai.config.CustomUserDetails;
import com.example.bandienthoai.config.UserService;
import com.example.bandienthoai.jwt.JwtTokenProvider;
import com.example.bandienthoai.model.Account;
import com.example.bandienthoai.payload.response.AccountResponse;
import com.example.bandienthoai.payload.request.LoginRequest;
import com.example.bandienthoai.payload.response.LoginResponse;
import com.example.bandienthoai.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "https://ban-dien-thoai-front-end-duyvotruongs-projects.vercel.app"})
public class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService customUserDetailsService;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> helloUser(){
        return new ResponseEntity<>("Hello User", HttpStatus.OK);
    }

    @GetMapping("/seller")
    public ResponseEntity<String> helloSeller(){
        return new ResponseEntity<>("Hello Seller", HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin(){
        return new ResponseEntity<>("Hello Admin", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        try {
            // Xác thực từ username và password.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal(); // Lấy customUserDetails từ authentication
            String jwt = tokenProvider.generateToken(customUserDetails);
            AccountResponse account = new AccountResponse();
            account.setId(customUserDetails.getUser().getId());
            account.setUsername(customUserDetails.getUsername());
            account.setRole(customUserDetails.getUser().getRole());
            return new ResponseEntity(new LoginResponse("success", jwt, account), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(new LoginResponse("false","",new AccountResponse()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Account acc){
        acc.setPassword(passwordEncoder.encode(acc.getPassword()));
        if(checkExist(acc)){
            throw new CustomException("400", "Email da ton tai!");
        }
        if(acc.getRole() == ""){
            acc.setRole(Role.USER.name().toUpperCase());
        } else if(acc.getRole().equalsIgnoreCase(Role.USER.name())){
            acc.setRole(Role.USER.name().toUpperCase());
        } else if(acc.getRole().equalsIgnoreCase(Role.SELLER.name())){
            acc.setRole(Role.SELLER.name().toUpperCase());
        } else if(acc.getRole().equalsIgnoreCase(Role.ADMIN.name())){
            acc.setRole(Role.ADMIN.name().toUpperCase());
        }
        Object savedAccount = accountRepository.save(acc);
        if(savedAccount==null){
            return new ResponseEntity<>("fail", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/getInforToJwt")
    public ResponseEntity  getInforToJwt(@RequestBody String jwt){
        try{

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                // Lấy id user từ chuỗi jwt
                Long userId = tokenProvider.getUserIdFromJWT(jwt);
                // Lấy thông tin người dùng từ id
                CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUserId(userId);
                if(userDetails != null) {
                    AccountResponse account = new AccountResponse();
                    account.setId(userDetails.getUser().getId());
                    account.setUsername(userDetails.getUsername());
                    account.setRole(userDetails.getUser().getRole());
                    return new ResponseEntity<>(new LoginResponse("success", jwt, account), HttpStatus.OK);
                }
            }
                return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        }
    }

    public boolean checkExist(Account account){
        if(accountRepository.findByUsername(account.getUsername())!=null){
            return true;
        } else {
            return false;
        }
    }
}
