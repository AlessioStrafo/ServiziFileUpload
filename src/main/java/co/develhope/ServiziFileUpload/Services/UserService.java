package co.develhope.ServiziFileUpload.Services;
import co.develhope.ServiziFileUpload.Entities.User;
import co.develhope.ServiziFileUpload.DTO.DownloadProfilePictureDTO;
import co.develhope.ServiziFileUpload.repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileStorageService fileStorageService;
    @SneakyThrows
    private User getUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) throw new Exception("User non presente");
        return optionalUser.get();
    }
    @SneakyThrows
    public User uploadProfilePicture(Long userId, MultipartFile profilePicture) {
        User user = getUser(userId);
        if(user.getProfilePictures()!=null){
            fileStorageService.remouve(user.getProfilePictures());
        }
        String fileName = fileStorageService.upload(profilePicture);
        user.setProfilePictures(fileName);
        return userRepository.save(user);
    }
    @SneakyThrows
    public DownloadProfilePictureDTO downloadProfilePicture(Long userId) {
        User user = getUser(userId);
        DownloadProfilePictureDTO dto = new DownloadProfilePictureDTO();
        dto.setUser(user);
        if(user.getProfilePictures()==null) return dto;
        byte[] profilePictureBytes = fileStorageService.downnload(user.getProfilePictures());
        dto.setProfileImage(profilePictureBytes);
        return dto;
    }
    @SneakyThrows
    public void remove(Long userId) {
        User user = getUser(userId);
        if(user.getProfilePictures()!=null) {
            fileStorageService.remouve(user.getProfilePictures());
        }
        userRepository.deleteById(userId);
    }
}
