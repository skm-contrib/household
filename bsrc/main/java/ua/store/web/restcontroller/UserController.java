package ua.store.web.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.store.domain.service.UserService;
import ua.store.web.dto.UserDTOResponse;
import ua.store.web.dto.auth.RegisterRequest;
import ua.store.web.dto.auth.UpdateUserDTO;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<UserDTOResponse> getUser(HttpServletRequest request){
        return ResponseEntity.ok(userService.getUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTOResponse> updateUser(@RequestBody @Valid UpdateUserDTO request, @PathVariable int id){
        return ResponseEntity.ok(userService.update(request, id));
    }

}
