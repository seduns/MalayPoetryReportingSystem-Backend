package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.config.JwtProvider;
import com.poetry.poetry_documentation_reporting.controller.AuthController;
import com.poetry.poetry_documentation_reporting.exception.EmailAlreadyExistsException;
import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Public;
import com.poetry.poetry_documentation_reporting.model.User;
import com.poetry.poetry_documentation_reporting.model.enumoption.USER_ROLE;
import com.poetry.poetry_documentation_reporting.model.enumoption.USER_STATUS;
import com.poetry.poetry_documentation_reporting.repository.AuthRepository;
import com.poetry.poetry_documentation_reporting.repository.AuthorRepository;
import com.poetry.poetry_documentation_reporting.repository.PublicRepository;
import com.poetry.poetry_documentation_reporting.request.AuthRequest;
import com.poetry.poetry_documentation_reporting.request.SignupRequest;
import com.poetry.poetry_documentation_reporting.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PublicRepository publicRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SystemUserDetailsService userDetailsService;

    @Override
    public Optional<User> findUserByEmail(String email) throws Exception {
        Optional<User> user = authRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new Exception("user not found for the " + email);
        }

        return user;
    }

    @Override
    public AuthResponse registerNewUser(SignupRequest user) throws Exception {

        Optional<User> existingUser = authRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already used for another account");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setFullName(user.getFullName());
        newUser.setRole(user.getRole());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = authRepository.save(newUser);

        if (user.getRole() == USER_ROLE.USER_PUBLIC) {
            Public publicUser = new Public();
            publicUser.setUser(savedUser); // @MapsId → ID auto ikut User ID
            publicRepository.save(publicUser);
            System.out.println("Mausk sinihhh");
        } else if (user.getRole() == USER_ROLE.USER_AUTHOR) {
            Author author = new Author();
            author.setUser(savedUser); // @MapsId → ID follows User ID
            author.setStatus(USER_STATUS.STATUS_ACTIVE);
            // Generate unique 4-digit publicId
            String publicId;
            do {
                publicId = String.format("%04d", (int)(Math.random() * 10000));
            } while (authorRepository.existsByPublicId(publicId)); // ensure uniqueness

            author.setPublicId(publicId);
            authorRepository.save(author);
            System.out.println("Created Author with publicId: " + publicId);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtProvider.generateAccessToken(authentication);
        String refreshToken = jwtProvider.generateFreshToken(user.getEmail());

        AuthResponse response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setStatus("register_success");
        response.setMessage("Register Success " + savedUser.getUsername());
        response.setRole(savedUser.getRole());
        response.setAccountId(savedUser.getId());

        return response;
    }


    @Override
    public AuthResponse loginTheUSer(AuthRequest authRequest) throws Exception {

        String email = authRequest.getEmail();
        String password = authRequest.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()
                ? null
                : authorities.iterator().next().getAuthority();

        String accessToken = jwtProvider.generateAccessToken(authentication);
        String freshToken = jwtProvider.generateFreshToken(email);

        Optional<User> user = authRepository.findByEmail(email);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setRefreshToken(freshToken);
        authResponse.setAccessToken(accessToken);
        authResponse.setMessage("Login Success");
        authResponse.setStatus("login_success");
        authResponse.setRole(USER_ROLE.valueOf(role));
        authResponse.setAccountId(user.get().getId());


        return authResponse;
    }


}
