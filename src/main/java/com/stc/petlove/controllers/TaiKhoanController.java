package com.stc.petlove.controllers;

import com.stc.petlove.dtos.user.UserDto;
import com.stc.petlove.entities.TaiKhoan;
import com.stc.petlove.services.taikhoan.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/petlove/tai-khoan")
public class TaiKhoanController {
    private final UserServiceImpl userService;

    public TaiKhoanController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserDto dto) {
        TaiKhoan addUser = userService.addUser(dto);
        if (addUser == null) {
            log.error("Add " + dto.getEmail() + " is failed, email is exist!!!");
            return new ResponseEntity<String>("email is exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("Add " + dto.getEmail() + " is succeed!!!");
        return new ResponseEntity<TaiKhoan>(addUser, HttpStatus.CREATED);
    }

    @GetMapping("/readOne")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        TaiKhoan findUser = userService.findUserByEmail(email);
        if (findUser == null) {
            log.error("email " + email + " is not exist!!!");
            return new ResponseEntity<String>("email " + email + " is not exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("read email " + email + " is succeed!!!");
        return new ResponseEntity<TaiKhoan>(findUser, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUserByEmail(@RequestBody UserDto dto) {
        TaiKhoan updateUser = userService.updateUserByEmail(dto);
        if (updateUser == null) {
            log.error("email " + dto.getEmail() + " is not exist!!!");
            return new ResponseEntity<String>("email " + dto.getEmail() + " is not exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("update email " + dto.getEmail() + " is succeed!!!");
        return new ResponseEntity<TaiKhoan>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserByEmail(@RequestParam String email) {
        boolean deleteUser = userService.deleteUserByEmail(email);
        if (!deleteUser) {
            log.error("email " + email + " is not exist!!!");
            return new ResponseEntity<String>("email " + email + " is not exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("read email " + email + " is succeed!!!");
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
