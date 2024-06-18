package com.agile.qdmp.standalong.channel.user;

import lombok.SneakyThrows;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * @Description: 用户名登录令牌
 * @Author: wenbinglei
 * @Date: 2020/12/2 11:16
 */
public class UserAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;
    private Object credentials;
    private String serverUri;

    public UserAuthenticationToken(String principal, Object credentials, String serverUri) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.serverUri = serverUri;
        setAuthenticated(false);
    }

    public UserAuthenticationToken(Object principal, Object credentials, String serverUri, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.serverUri = serverUri;
        super.setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    public String getServerUri() {
        return this.serverUri;
    }

    @Override
    @SneakyThrows
    public void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }

}
