package br.com.wcorrea.transport.api.security;

import br.com.wcorrea.transport.api.model.seguranca.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class SystemUser extends User {

    private static final long serialVersionUID = 1L;

    private Usuario usuarioModel;

    public SystemUser(Usuario usuarioParametro, Collection<? extends GrantedAuthority> authorities) {
        super(usuarioParametro.getEmail(), usuarioParametro.getSenha(), authorities);
        this.usuarioModel = usuarioParametro;
    }

    public Usuario getUser() {
        return usuarioModel;
    }

}
