package cake.box.waracle.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cake.box.waracle.dto.userInfo;

public interface userRepo  extends JpaRepository<userInfo,Integer>{
	Optional<userInfo> findByUsername(String username);
}
