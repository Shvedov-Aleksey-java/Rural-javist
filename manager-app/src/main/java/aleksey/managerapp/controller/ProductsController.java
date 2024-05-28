package aleksey.managerapp.controller;


import aleksey.managerapp.entity.Product;
import aleksey.managerapp.payload.NewProductPayload;
import aleksey.managerapp.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/list")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "list";
    }

    @GetMapping("/create")
    public String getNewProductPage() {
        return "new_product";
    }

    @PostMapping("/create")
    public String createProduct(@Valid NewProductPayload productPayload, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            model.addAttribute("payload", productPayload);
            return "new_product";
        } else {
            Product product = this.productService.create(productPayload.details(), productPayload.title());
            return "redirect:list";
        }

    }
}
