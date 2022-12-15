package com.emanage;

import com.emanage.model.dto.user.UserDTO;
import com.emanage.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:apps.properties")
public class EmanageApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(EmanageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserDTO updateUserDTO = new UserDTO();

        updateUserDTO.setUserID(1);
        updateUserDTO.setPassword("password");

//        this.userService.updateUser(updateUserDTO);
    }
}
