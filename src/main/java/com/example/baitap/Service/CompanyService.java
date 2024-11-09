package com.example.baitap.Service;

import com.example.baitap.Entity.Company;
import com.example.baitap.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Lấy danh sách tất cả các công ty
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    // Lưu hoặc cập nhật công ty
    public Company saveOrUpdate(Company company) {
        companyRepository.save(company);
        return company;
    }
//    public void saveOrUpdate(Company company) {
//        companyRepository.save(company);
//    }

    // Tìm công ty theo ID
    public Company findById(int id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElse(null);
    }

    // Xóa công ty theo ID
    public void deleteById(int id) {
        companyRepository.deleteById(id);
    }
}