package aleksey.managerapp.controller;

import aleksey.managerapp.entity.Product;
import aleksey.managerapp.payload.UpdateProductPayload;
import aleksey.managerapp.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId}")
public class ProductController {

    private final ProductService productService;
    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") Integer productId) {
        return productService.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public String getProduct() {
        return "product";
    }

    @GetMapping("/edit")
    public String getProductEditPage() {
        return "edit";
    }

    @PostMapping("/edit")
    public String UpdateProductEditPage(@ModelAttribute(value = "product", binding = false) Product product,
                                        UpdateProductPayload payload) {
        this.productService.productUdate(product.getId(), payload.title(), payload.details());
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }

    @GetMapping("/remove")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productService.remove(product.getId());
        return "redirect:/catalogue/products/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model,
                                               HttpServletResponse response, Locale locale) {

        response.setStatus(404);
        model.addAttribute("error",
                this.messageSource.getMessage(exception.getMessage(), new Object[0],
                exception.getMessage(), locale));
        return "404";
    }


}
