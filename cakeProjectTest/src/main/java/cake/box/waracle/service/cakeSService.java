package cake.box.waracle.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cake.box.waracle.dao.cakeDao;
import cake.box.waracle.dao.cakeRepo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cake.box.waracle.dao.userRepo;
import cake.box.waracle.dto.cakeEntity;
import cake.box.waracle.dto.userInfo;

@Service
public class cakeSService implements UserDetailsService  {
	
	@Autowired
	cakeDao dao;

	@Autowired
	cakeRepo repo;
	
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	
	@Autowired
	userRepo urepo;
	
	public List<cakeEntity> getCakes(){
		return dao.getCakes();
	}

  public Integer saveUser(userInfo info) {
	  
	  info.setPassword(
				pwdEncoder.encode(info.getPassword())
				);
	  
	 return  urepo.save(info).getId();
	  
	  
	  
  }
	 public List<cakeEntity> addCakes(List<cakeEntity> bean){
		 
		List<cakeEntity>  ce = repo.saveAll(bean);
		 
		 //repo.findAll();
		
		List<cakeEntity>  ce1 = dao.getCakes();
		 return ce1;
		 
	 }
	 public userInfo findByUsername(String username) {
			Optional<userInfo> user=urepo.findByUsername(username);
			if(user.isPresent()) 
				return user.get();
			return null;
		}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("3");
		// TODO Auto-generated method stub
		userInfo user=findByUsername(username); 
		if(user==null) 
			throw new UsernameNotFoundException(
					new StringBuffer()
					.append("User name ")
					.append(username)
					.append(" not found!")
					.toString()
					);

		List<GrantedAuthority> authorities=
				user.getRoles()
				.stream()
				.map(
						role->new SimpleGrantedAuthority(role)
						)
				.collect(Collectors.toList());
		System.out.println("4");

		return new org.springframework.security.core.userdetails.User(
				username, 
				user.getPassword(), 
				authorities);
	}
	  

}
