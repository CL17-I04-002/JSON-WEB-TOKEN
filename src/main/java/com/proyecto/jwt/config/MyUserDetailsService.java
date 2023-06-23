package com.proyecto.jwt.config;

import com.proyecto.jwt.model.Users;
import com.proyecto.jwt.model.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    /**
     * Cargaremos el usuario actual y lo iremos a buscar a nuestra bd
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userFound = usersRepository.findUserByUsername(username);
        if(userFound!=null){
            return userFound;
        }else{
            throw new UsernameNotFoundException("User has not been found");
        }
    }
    public UserDetails loadByUsernameAndPassword(String username, String password) throws UsernameNotFoundException{
        Users userFound = usersRepository.findUserByUsernameAndPassword(username, password);
        if(userFound!=null){
            return userFound;
        }else{
            throw new UsernameNotFoundException("User has not been found");
        }
    }
}
