package co.develhope.ServiziFileUpload.Services;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {
    @Value("{fileRepositoryFolder}")
    private String fileRepositoryFolder;

    @SneakyThrows
    public String upload(MultipartFile file){
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString();
//      String completeFileName = newFileName + "." + extension;
        String completeFileName = file.getOriginalFilename();
        //verifiche di repository folder
        File finalFolder = new File(fileRepositoryFolder);
        if(!finalFolder.exists())throw new IOException("folder non esistente");
        if(!finalFolder.isDirectory())throw new IOException("directory non esistente");
        File fileDestination = new File(fileRepositoryFolder + "\\" + completeFileName);
        if(fileDestination.exists()) throw new IOException("file gia esistente");
        file.transferTo(fileDestination);
        return completeFileName;
    }
    public byte[] downnload(String fileName) throws IOException {
        File fileFromRepository = new File(fileRepositoryFolder + "\\" + fileName);
        if(!fileFromRepository.exists()) throw new IOException("file non esistente");
        return IOUtils.toByteArray(new FileInputStream(fileFromRepository));
    }
}
