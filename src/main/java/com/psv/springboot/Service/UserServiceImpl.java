package com.psv.springboot.Service;

import com.psv.springboot.Dto.UserRegistrationDto;
import com.psv.springboot.Models.Role;
import com.psv.springboot.Models.User;
import com.psv.springboot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class UserServiceImpl {
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository=userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto){
        User user=new User(registrationDto.getFirstName(),
                registrationDto.getLastName(),registrationDto.getEmail(),passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("Role_User")));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsernname(String username) throws UsernameNotFoundException{
        User user= userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),getPassword(),mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<?extends GrantedAuthority>mapRolesToAuthorities(Collection<Role>roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName());
    }

}
