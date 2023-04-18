package com.stc.petlove.controllers;

import com.stc.petlove.dtos.dichvu.DichVuDto;
import com.stc.petlove.entities.DichVu;
import com.stc.petlove.services.dichvu.DichVuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/petlove/dich-vu")
public class DichVuController {
    private final DichVuServiceImpl dichVuService;

    public DichVuController(DichVuServiceImpl dichVuService) {
        this.dichVuService = dichVuService;
    }

    @PostMapping
    public ResponseEntity<?> addDichVu(@RequestBody DichVuDto dto) {
        DichVu addDichVu = dichVuService.addDichVu(dto);
        if (addDichVu == null) {
            log.error("Add " + dto.getMaDichVu() + " is failed, maLoai is exist!!!");
            return new ResponseEntity<String>("maDichVu is exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("Add " + dto.getMaDichVu() + " is succeed!!!");
        return new ResponseEntity<DichVu>(addDichVu, HttpStatus.CREATED);
    }

    @GetMapping("/readOne")
    public ResponseEntity<?> getDichVuByMaLoai(@RequestParam String maDV) {
        DichVu findDichVu = dichVuService.findDichVuByMaDV(maDV);
        if (findDichVu == null) {
            log.error("maDV " + maDV + " is not exist!!!");
            return new ResponseEntity<String>("maDV " + maDV + " is not exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("read maDV " + maDV + " is succeed!!!");
        return new ResponseEntity<DichVu>(findDichVu, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateDichVuByMaLoai(@RequestBody DichVuDto dto) {
        DichVu updateDichVu = dichVuService.updateDichVuByMaDV(dto);
        if (updateDichVu == null) {
            log.error("maDV " + dto.getMaDichVu() + " is not exist!!!");
            return new ResponseEntity<String>("maDV " + dto.getMaDichVu() + " is not exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("update maDV " + dto.getMaDichVu() + " is succeed!!!");
        return new ResponseEntity<DichVu>(updateDichVu, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDichVuByMaLoai(@RequestParam String maDV) {
        boolean deleteDichVu = dichVuService.deleteDichVuByMaDV(maDV);
        if (!deleteDichVu) {
            log.error("maDV " + maDV + " is not exist!!!");
            return new ResponseEntity<String>("maDV " + maDV + " is not exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("read maDV " + maDV + " is succeed!!!");
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
