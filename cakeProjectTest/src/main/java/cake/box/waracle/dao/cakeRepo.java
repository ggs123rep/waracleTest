package cake.box.waracle.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cake.box.waracle.dto.cakeEntity;

public interface cakeRepo extends JpaRepository<cakeEntity,Integer>{

}
