package com.jai.services;

import com.jai.daos.UserDao;
import com.jai.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private UserDao userDao;
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username){
        User user  = userDao.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found.");
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getRoles().stream().
                map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
        return userDetails;
    }

    public User update(User user){
        User userdao = userDao.findById(user.getId())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        userdao.setName(user.getName());
        userdao.setEmail(user.getEmail());
        userdao.setActive(user.isActive());
        userdao.setGender(user.getGender());
        userdao.setPassword(user.getPassword());
        userdao.setRoles(user.getRoles());
        userdao.setUsername(user.getUsername());
        return userDao.save(userdao);
    }
    public User create(User user){
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        return userDao.save(user);
    }

    public List<User> users(){
        return userDao.findAll();
    }



}
