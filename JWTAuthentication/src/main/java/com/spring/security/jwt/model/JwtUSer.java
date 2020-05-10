package com.spring.security.jwt.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUSer implements UserDetails {
	private static final long serialVersionUID = 1L;
	private final long id;
	private final String userName;
	private final String password;
	private final User user;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;

	public JwtUSer(long id, String userName, String password, User user,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.user = user;
		this.authorities = authorities;
		this.enabled = enabled;
	}
    @JsonIgnore
	public long getId() {
		return id;
	}
     
	public String getPassword() {
		return password;
	}

	public User getUser() {
		return user;
	}
	
     @Override
	public boolean isEnabled() {
		return enabled;
	}

     
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
     
	
	@Override
	public String getUsername() {
		return userName;
	}

	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
    
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
