package com.example.SpringSecurity.service;

import com.example.SpringSecurity.entity.AppUser;
import com.example.SpringSecurity.repo.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService  implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IAppUserRepo appUserRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = appUserRepo.findByUsername(username);
        if(userDetails == null)
        {
            throw new UsernameNotFoundException("User does not exist");
        }
        return userDetails;
    }

    public long createUser(AppUser user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepo.save(user);
        return user.getId();
    }

    public long changePassword(UserDetails userDetails, String newPassword)
    {
        AppUser appUser = appUserRepo.findByUsername(userDetails.getUsername());
        appUser.setPassword(passwordEncoder.encode(newPassword));
        appUserRepo.save(appUser);
        return appUser.getId();
    }
}
