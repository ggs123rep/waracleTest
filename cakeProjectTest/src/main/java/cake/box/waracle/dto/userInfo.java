package cake.box.waracle.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="users")

public class userInfo {
@javax.persistence.Id
@GeneratedValue
	private Integer Id;
	private String username;
	private String password;
	 @ElementCollection( fetch = FetchType.EAGER)
	 @CollectionTable(
			 name="roles",
			 joinColumns=@JoinColumn(name="Id")
			 )
	 @Column(name="role")
	// @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<String> roles;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	} 
	
	
	
}
