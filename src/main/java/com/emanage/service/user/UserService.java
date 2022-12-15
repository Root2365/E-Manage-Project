package com.emanage.service.user;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.dao.role.RoleDao;
import com.emanage.dao.user.UserDao;
import com.emanage.exception.AppsException;
import com.emanage.model.domain.role.Role;
import com.emanage.model.domain.user.User;
import com.emanage.model.dto.user.ChangePasswordDTO;
import com.emanage.model.dto.user.UserDTO;
import com.emanage.model.dto.user.UserUpdateRQ;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.util.CalendarUtil;
import com.emanage.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public UserDTO getUserByID(Integer userID) throws AppsException {
        User user = this.userDao.getById(userID);
        return new UserDTO(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public UserDTO updateUser(UserUpdateRQ updateRQ, CredentialsDTO credentialsDTO) throws AppsException {
        User user = this.userDao.getById(updateRQ.getUserID());

        user.setUserName(updateRQ.getUserName());
        user.setFirstName(updateRQ.getFirstName());
        user.setLastName(updateRQ.getLastName());
        user.setEmail(updateRQ.getEmail());
        user.setDateOfBirth(CalendarUtil.getDefaultParsedDateOnly(updateRQ.getDateOfBirthStr()));
        user.setModifiedDate(new Date());
        user.setModifiedBy(credentialsDTO.getUserID());

        user = userDao.saveAndFlush(user);

        return new UserDTO(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public String generateEncodedPassword(String plainPassword) throws AppsException {
        return PasswordUtil.generateEncodedPassword(new BCryptPasswordEncoder(), plainPassword);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public UserDTO registerUser(UserDTO registerUserDTO) throws AppsException {

        if (this.userDao.getByEmail(registerUserDTO.getEmail()) != null) {
            throw new AppsException("User email already exists in the system ");
        }

        if (this.userDao.getByUserName(registerUserDTO.getUserName()) != null) {
            throw new AppsException("User name already exists in the system ");
        }

        User user = new User();

        user.setUserName(registerUserDTO.getUserName());
        user.setFirstName(registerUserDTO.getFirstName());
        user.setLastName(registerUserDTO.getLastName());
        user.setPassword(this.generateEncodedPassword(registerUserDTO.getPassword()));
        user.setEmail(registerUserDTO.getEmail());
        user.setCreatedDate(new Date());
        user.setDateOfBirth(CalendarUtil.getDefaultParsedDateOnly(registerUserDTO.getDateOfBirthStr()));
        user.setStatus(AppsConstants.Status.ACT);
        user.setIsAdmin(registerUserDTO.getIsAdmin());

        Role role;

        if (registerUserDTO.getIsAdmin() == AppsConstants.YesNo.Y) {
            role = this.roleDao.getByRoleName("Admin");
        } else {
            role = this.roleDao.getByRoleName("User");
        }

        user.getRoles().add(role);

        user = userDao.saveAndFlush(user);

        return new UserDTO(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public UserDTO changeUserPassword(ChangePasswordDTO changePasswordDTO, CredentialsDTO credentialsDTO) throws AppsException {
        User user = this.userDao.getById(changePasswordDTO.getUserID());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String oldPassword = changePasswordDTO.getOldPassword();
        String existingPassword = user.getPassword();

        if (passwordEncoder.matches(oldPassword, existingPassword)) {
            String encodedPassword = this.generateEncodedPassword(changePasswordDTO.getNewPassword());

            user.setPassword(encodedPassword);
            user.setModifiedBy(credentialsDTO.getUserID());
            user.setModifiedDate(new Date());

            user = this.userDao.saveAndFlush(user);

            return new UserDTO(user);
        } else {
            throw new AppsException("Password mismatch");
        }
    }
}
