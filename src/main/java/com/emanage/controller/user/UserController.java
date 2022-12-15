package com.emanage.controller.user;

import com.emanage.commons.constants.AppsConstants;
import com.emanage.controller.BaseController;
import com.emanage.exception.AppsException;
import com.emanage.model.common.ResponseDTO;
import com.emanage.model.dto.user.ChangePasswordDTO;
import com.emanage.model.dto.user.UserDTO;
import com.emanage.model.dto.user.UserUpdateRQ;
import com.emanage.model.security.CredentialsDTO;
import com.emanage.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${api.prefix}/user")
public class UserController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUserByID/{userID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<UserDTO>> getUserByID(@PathVariable Integer userID) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : Get User : {} by user user {}", userID, credentialsDTO.getUserID());

        try {
            UserDTO userDTO = this.userService.getUserByID(userID);
            response.setResult(userDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : get User : {} by user user {}", userID, credentialsDTO.getUserID());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/registerUser", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<UserDTO>> registerUser(@RequestBody UserDTO registerUserDTO) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();

        LOG.info("START : Register user : {} ", registerUserDTO);

        try {
            UserDTO userDTO = this.userService.registerUser(registerUserDTO);
            response.setResult(userDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Register user : {} ", registerUserDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/changeUserPassword", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<UserDTO>> changeUserPassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();

        LOG.info("START : Change password by user : {} ", changePasswordDTO);

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        try {
            UserDTO userDTO = this.userService.changeUserPassword(changePasswordDTO, credentialsDTO);
            response.setResult(userDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Change password by user : {} ", changePasswordDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/updateUser", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<UserDTO>> updateUser(@RequestBody UserUpdateRQ updateRQ) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();

        LOG.info("START : Update user : {} ", updateRQ);
        CredentialsDTO credentialsDTO = getCredentialsDTO();

        try {
            UserDTO userDTO = this.userService.updateUser(updateRQ, credentialsDTO);
            response.setResult(userDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        LOG.info("END : Update user : {} ", updateRQ);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
