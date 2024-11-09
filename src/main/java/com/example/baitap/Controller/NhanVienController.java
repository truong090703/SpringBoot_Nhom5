package com.example.baitap.Controller;

import com.example.baitap.Entity.NhanVien;
import com.example.baitap.Service.CompanyService;
import com.example.baitap.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/nhanviens")
    public String listNhanViens(Model model) {
        model.addAttribute("nhanviens", nhanVienService.getAllNhanViens());
        return "nhanvienList";
    }

    @GetMapping("/addNhanVien")
    public String addNhanVienForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("companies", companyService.getAll());
        return "addNhanVien";
    }

    @PostMapping("/addNhanVien")
    public String saveNhanVien(@ModelAttribute("nhanvien") NhanVien nhanVien) {
        nhanVienService.saveOrUpdate(nhanVien);
        return "redirect:/nhanviens";
    }

    @GetMapping("/editNhanVien/{id}")
    public String editNhanVienForm(@PathVariable int id, Model model) {
        NhanVien nhanVien = nhanVienService.getNhanVienById(id);
        model.addAttribute("nhanvien", nhanVien);
        model.addAttribute("companies", companyService.getAll());
        return "editNhanVien";
    }

    @PostMapping("/editNhanVien/{id}")
    public String updateNhanVien(@PathVariable int id, @ModelAttribute("nhanvien") NhanVien nhanVien) {
        nhanVienService.saveOrUpdate(nhanVien);
        return "redirect:/nhanviens";
    }

    @GetMapping("/deleteNhanVien/{id}")
    public String deleteNhanVien(@PathVariable int id) {
        nhanVienService.deleteNhanVien(id);
        return "redirect:/nhanviens";
    }
}
