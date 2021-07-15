package com.my.task.productcatalogadmin.service;

import com.my.task.productcatalogadmin.model.SecurityUser;
import com.my.task.productcatalogadmin.model.Person;
import com.my.task.productcatalogadmin.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("personDetailsServiceImpl")
public class PersonDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login doesn't exist : " + login));
        return SecurityUser.fromUser(person);
    }
}
