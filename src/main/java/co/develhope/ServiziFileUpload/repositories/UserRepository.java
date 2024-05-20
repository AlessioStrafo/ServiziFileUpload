package co.develhope.ServiziFileUpload.repositories;

import co.develhope.ServiziFileUpload.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
