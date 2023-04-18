package com.stc.petlove.controllers;

import com.stc.petlove.dtos.loaithucung.LoaiThuCungDto;
import com.stc.petlove.entities.LoaiThuCung;
import com.stc.petlove.services.loaithucung.LoaiThuCungServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/petlove/loai-thu-cung")
public class LoaiThuCungController {
    private final LoaiThuCungServiceImpl loaiThuCungService;

    public LoaiThuCungController(LoaiThuCungServiceImpl loaiThuCungService) {
        this.loaiThuCungService = loaiThuCungService;
    }

    @PostMapping
    public ResponseEntity<?> addLoaiThuCung(@RequestBody LoaiThuCungDto dto) {
        LoaiThuCung addLoaiThuCung = loaiThuCungService.addLoaiThuCung(dto);
        if (addLoaiThuCung == null) {
            log.error("Add " + dto.getMaLoaiThuCung() + " is failed, maLoai is exist!!!");
            return new ResponseEntity<String>("maLoaiThuCung is exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("Add " + dto.getMaLoaiThuCung() + " is succeed!!!");
        return new ResponseEntity<LoaiThuCung>(addLoaiThuCung, HttpStatus.CREATED);
    }

    @GetMapping("/readOne")
    public ResponseEntity<?> getLoaiThuCungByMaLoai(@RequestParam String maLoai) {
        LoaiThuCung findLoaiThuCung = loaiThuCungService.findLoaiThuCungByMaLoai(maLoai);
        if (findLoaiThuCung == null) {
            log.error("maLoai " + maLoai + " is not exist!!!");
            return new ResponseEntity<String>("maLoai " + maLoai + " is not exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("read maLoai " + maLoai + " is succeed!!!");
        return new ResponseEntity<LoaiThuCung>(findLoaiThuCung, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateLoaiThuCungByMaLoai(@RequestBody LoaiThuCungDto dto) {
        LoaiThuCung updateLoaiThuCung = loaiThuCungService.updateLoaiThuCungByMaLoai(dto);
        if (updateLoaiThuCung == null) {
            log.error("maLoai " + dto.getMaLoaiThuCung() + " is not exist!!!");
            return new ResponseEntity<String>("maLoai " + dto.getMaLoaiThuCung() + " is not exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("update maLoai " + dto.getMaLoaiThuCung() + " is succeed!!!");
        return new ResponseEntity<LoaiThuCung>(updateLoaiThuCung, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLoaiThuCungByMaLoai(@RequestParam String maLoai) {
        boolean deleteLoaiThuCung = loaiThuCungService.deleteLoaiThuCungByMaLoai(maLoai);
        if (!deleteLoaiThuCung) {
            log.error("maLoai " + maLoai + " is not exist!!!");
            return new ResponseEntity<String>("maLoai " + maLoai + " is not exist!!!", HttpStatus.BAD_REQUEST);
        }
        log.info("read maLoai " + maLoai + " is succeed!!!");
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
