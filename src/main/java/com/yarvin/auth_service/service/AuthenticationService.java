package com.yarvin.auth_service.service;

import com.yarvin.auth_service.repository.RoleRepository;
import com.yarvin.auth_service.repository.UserRepository;
import com.yarvin.auth_service.store.dto.*;
import com.yarvin.auth_service.store.entity.RoleEntity;
import com.yarvin.auth_service.store.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public JwtAuthenticationResponseDto signUp(SignUpDto request) {
        RoleEntity role =  roleRepository.findByName("User");
        var user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)

                .build();
        System.out.println(user.getEmail());
        userService.create(user);

        var jwt = jwtService.generateToken(user);
        System.out.println(jwt);
        return new JwtAuthenticationResponseDto(jwt,role.getName());
    }


    public JwtAuthenticationResponseDto signIn(SignInDto request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);

        var user_id = userService.getByUsername(user.getUsername());
        var role  = roleRepository.findByid(user_id.getRole().getId());

        return new JwtAuthenticationResponseDto(jwt,role.getName());
    }

    public AuthentificationTokenResponseDto validateToken(TokenDto request) throws ErrorDto {
        if(jwtService.isTokenValid(request.getToken())){
            var username = jwtService.extractUserName(request.getToken());
            var user = userService.getByUsername(username);
            var role_id = user.getRole();
            var role = roleRepository.findByid(role_id.getId());
            return new AuthentificationTokenResponseDto(
                    username,user.getEmail(),role.getName(),
                    user.getId(),user.getIsSponsor(),user.getInn(),
                    user.getOgrn(),user.getSponsorAdress(),user.getName(),
                    user.getSecondName(),user.getFatherName(),user.getCity(),
                    user.getDateOfBirth());
        }

        throw new ErrorDto("Токен не валиден");
    }

    public AuthentificationIdResponseDto findById(UuidDto request) {
        var user = userRepository.findById(request.getId());
        var role = roleRepository.findById(user.get().getRole().getId());
        return new AuthentificationIdResponseDto(
                user.get().getUsername(), user.get().getEmail(),
                role.get().getName(),user.get().getIsSponsor(),
                user.get().getInn(), user.get().getOgrn(),
                user.get().getSponsorAdress(),user.get().getName(),
                user.get().getSecondName(),user.get().getFatherName(),
                user.get().getCity(), user.get().getDateOfBirth());
    }
}
