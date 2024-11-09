package com.example.baitap.Repository;

import com.example.baitap.Entity.NhanVien;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends CrudRepository<NhanVien, Integer> {
}
