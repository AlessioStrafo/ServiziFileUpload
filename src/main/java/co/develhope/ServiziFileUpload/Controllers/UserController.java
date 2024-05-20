package co.develhope.ServiziFileUpload.Controllers;

import co.develhope.ServiziFileUpload.DTO.DownloadProfilePictureDTO;
import co.develhope.ServiziFileUpload.Entities.User;
import co.develhope.ServiziFileUpload.Services.UserService;
import co.develhope.ServiziFileUpload.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @PostMapping
    public User create(@RequestBody User user){
        user.setId(null);
        return userRepository.save(user);
    }
    @SneakyThrows
    @PostMapping("/(id)/profile")
    public User uploadProfileImage(@PathVariable Long id, @RequestParam MultipartFile profilePicture) {
        return userService.uploadProfilePicture(id, profilePicture);
    }
    @GetMapping
    public List<User> getAll(){
        return userRepository.findAll();
    }
    @GetMapping("/(id)")
    public Optional<User> getOne(@PathVariable Long id){
        return userRepository.findById(id);
    }
    @SneakyThrows
    @GetMapping("/(id)/profile")
    public @ResponseBody byte[] getProfileImage(@PathVariable Long id, HttpServletResponse response){
        DownloadProfilePictureDTO downloadProfileDTO = userService.downloadProfilePicture(id);
        String fileName = downloadProfileDTO.getUser().getProfilePictures();
        if(fileName==null) throw new Exception("User non ha una immagine del profilo");
        String extension = FilenameUtils.getExtension(downloadProfileDTO.getUser().getProfilePictures());
        switch (extension){
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpg":
            case "jpeg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
        }
        response.setHeader("contenent-disposition", "attachment; filename=\""+fileName+"\"");
        return downloadProfileDTO.getProfileImage();
    }
    @PutMapping("/(id)")
    public void update(@RequestBody User user, @PathVariable Long id){
        user.setId(id);
        userRepository.save(user);
    }
    @SneakyThrows
    @DeleteMapping("/(id)")
    public void delete(@PathVariable Long id){
        userService.remove(id);
    }
}
