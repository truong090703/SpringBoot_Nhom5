package com.example.baitap.Repository;

import com.example.baitap.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    // Các phương thức truy vấn tùy chỉnh (nếu cần)
}
