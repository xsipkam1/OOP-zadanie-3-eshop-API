package sk.stuba.fei.uim.oop.assignment3.cart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.cart.service.ICartService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService service;

    @PostMapping()
    public ResponseEntity<CartResponse> createCart() {
        return new ResponseEntity<>(new CartResponse(this.service.createCart()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public CartResponse getCart(@PathVariable("id") Long id) throws NotFoundException {
        return new CartResponse(this.service.getCart(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable("id") Long id) throws NotFoundException {
        this.service.deleteCart(id);
    }


    @PostMapping("/{id}/add")
    public ResponseEntity<CartResponse> addToCart(@PathVariable("id") Long id, @RequestBody CartRequest body) throws NotFoundException, IllegalOperationException {
        return new ResponseEntity<>(new CartResponse(this.service.addToCart(id, body)), HttpStatus.OK);
    }

    @GetMapping("/{id}/pay")
    public ResponseEntity<Double> payForCart(@PathVariable("id") Long id) throws NotFoundException, IllegalOperationException {
        return new ResponseEntity<>(this.service.payForCart(id), HttpStatus.OK);
    }

}
