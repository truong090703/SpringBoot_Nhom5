package nhom3.spring.thanh.baitap1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/trangchu")
    public String trangChu(Model model) {
        model.addAttribute("lop", "Thông tin lớp học: Lớp Lập trình Web");
        return "trangchu"; // Trả về trang HTML "trangchu.html"
    }
}