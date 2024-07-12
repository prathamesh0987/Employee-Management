package com.psv.springboot.Service;

import com.psv.springboot.Dto.UserRegistrationDto;
import com.psv.springboot.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
