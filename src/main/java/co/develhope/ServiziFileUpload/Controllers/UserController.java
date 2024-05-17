package co.develhope.ServiziFileUpload.Controllers;

import co.develhope.ServiziFileUpload.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @PostMapping
    public void create(){

    }
    @PostMapping("/(id)/profile")
    public void uploadProfileImage(){

    }
    @GetMapping
    public void getAll(){

    }
    @GetMapping("/(id)")
    public void getOne(){

    }
    @GetMapping("/(id)/profile")
    public void getProfileImage(){

    }
    @PutMapping("/(id)")
    public void update(){

    }
    @DeleteMapping("/(id)")
    public void delete(){

    }


}
