package venter.mircea.onlinestore.controller;

import venter.mircea.onlinestore.configuration.security.JwtTokenUtil;
import venter.mircea.onlinestore.model.dto.AuthRequestDto;
import venter.mircea.onlinestore.model.dto.UserDto;
import venter.mircea.onlinestore.model.entity.UserEntity;
import venter.mircea.onlinestore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                    ModelMapper modelMapper, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthRequestDto request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
                    );

            UserEntity userEntity = (UserEntity) authentication.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(userEntity))
                    .body(modelMapper.map(userEntity, UserDto.class));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Username or password is wrong!");
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please verify your account first!");
        }
    }

    @PutMapping("/userConfirmation")
    public ResponseEntity<String> method(@RequestParam("userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.confirmUser(userId));
    }
}
