package ua.store.web.restcontroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.store.domain.model.Product;
import ua.store.domain.service.ProductService;
import ua.store.web.dto.ProductDTORequest;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProductsByPage(@RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                                              @RequestParam(required = false, defaultValue = "20", value = "count") int count,
                                                              @RequestParam(required = false, value = "category_id") Integer categoryId,
                                                              @RequestParam(required = false, value = "search") String search) {
        return ResponseEntity.ok(productService.findAll(PageRequest.of(page, count), categoryId, search));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCountOfProducts(){
        return ResponseEntity.ok(productService.count());
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductDTORequest product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductDTORequest product, @PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.update(product, id));
    }

    @DeleteMapping("/{id}/disable")
    public ResponseEntity<String> updateProduct(@PathVariable int id) {
        return ResponseEntity.ok().body(productService.disable(id) ? "Product with id: " + id + " has been deleted" : "Product not deleted");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return ResponseEntity.ok().body(productService.deleteById(id) ? "Product with id: " + id + " has been deleted" : "Product not deleted");
    }
}
