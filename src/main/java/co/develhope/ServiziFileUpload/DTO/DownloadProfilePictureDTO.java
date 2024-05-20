package co.develhope.ServiziFileUpload.DTO;

import co.develhope.ServiziFileUpload.Entities.User;
import lombok.Data;

@Data
public class DownloadProfilePictureDTO {
    private User user;
    private byte[] profileImage;
}
