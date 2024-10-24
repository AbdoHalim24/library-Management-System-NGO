package com.NGO.libraryManagementSystem.Service.Imp;

import com.NGO.libraryManagementSystem.DTO.LoginDto;
import com.NGO.libraryManagementSystem.DTO.SavedUserDto;
import com.NGO.libraryManagementSystem.DTO.UserDto;
import com.NGO.libraryManagementSystem.Entity.Role;
import com.NGO.libraryManagementSystem.Entity.User;
import com.NGO.libraryManagementSystem.Mapper.UserMap;
import com.NGO.libraryManagementSystem.Repository.UserRepository;
import com.NGO.libraryManagementSystem.Security.JwtService;
import com.NGO.libraryManagementSystem.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMap userMapper;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder
            , AuthenticationManager authenticationManager, JwtService jwtService, UserMap userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @Override
    public String login(LoginDto loginDto) {
        User user=userRepository.findByEmail(loginDto.getEmail());

        if (user == null || !passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return "Invalid Email or Password";
        }
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
        }

        // User authenticated, generate JWT token
        var jwt = jwtService.generateToken(new CustomUserDetails(user));
        return jwt;
    }

    @Override
    public SavedUserDto Register(UserDto userDto) {
        User user=new User();

        user.setUsername(userDto.getUsername());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.valueOf(userDto.getRole().toUpperCase()));
        User user1=userRepository.save(user);

        String email=user1.getUsername()+"_"+user1.getId();
        user1.setEmail(email);

        User user2= userRepository.save(user1);
        return userMapper.UserToSavedUserDto(user2);
    }



}
