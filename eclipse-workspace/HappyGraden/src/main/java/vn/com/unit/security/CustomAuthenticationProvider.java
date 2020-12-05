package vn.com.unit.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import vn.com.unit.entity.Account;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.RoleService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RoleService roleService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String rawPassword = authentication.getCredentials().toString();

		Account account = accountService.findByUsername(username);

		if (account != null && accountService.checkLogin(account, rawPassword)) {

			if (account.isAccountDisable() == true) {
				throw new BadCredentialsException("Your account has been deleted");
			}
			
			List<GrantedAuthority> authorities = new ArrayList<>();

			try {
				authorities = roleService.findAuthorities(account);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if (authorities.isEmpty()) {
				throw new BadCredentialsException("Your account has been deactivated");
			}

			return new UsernamePasswordAuthenticationToken(username, rawPassword, authorities);
		}
		
		throw new BadCredentialsException("Wrong username or password");

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

