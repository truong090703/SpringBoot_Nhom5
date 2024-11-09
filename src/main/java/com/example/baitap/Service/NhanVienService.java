package com.example.baitap.Service;

import com.example.baitap.Entity.NhanVien;
import com.example.baitap.Repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public NhanVien getNhanVienById(int id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    public List<NhanVien> getAllNhanViens() {
        return (List<NhanVien>) nhanVienRepository.findAll();
    }

    public NhanVien saveOrUpdate(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
        return nhanVien;
    }

    public void deleteNhanVien(int id) {
        nhanVienRepository.deleteById(id);
    }

}

