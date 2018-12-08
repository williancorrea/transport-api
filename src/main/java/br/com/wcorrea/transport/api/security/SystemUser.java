package br.com.wcorrea.transport.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class SystemUser extends User {

	private static final long serialVersionUID = 1L;

	private br.com.wcorrea.transport.api.model.seguranca.User userModel;

	public SystemUser(br.com.wcorrea.transport.api.model.seguranca.User userParam, Collection<? extends GrantedAuthority> authorities) {
		super(userParam.getEmail(), userParam.getPassword(), authorities);
		this.userModel = userParam;
	}

	public br.com.wcorrea.transport.api.model.seguranca.User getUser() {
		return userModel;
	}

}
