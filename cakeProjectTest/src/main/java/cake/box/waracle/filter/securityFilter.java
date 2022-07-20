package cake.box.waracle.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cake.box.waracle.util.JwtUtil;

@Component
public class securityFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
					throws ServletException, IOException {
System.out.println("this is filter");
		String token=request.getHeader("Authorization");
		if(token!=null) {

			String username=jwtUtil.getUsername(token);
			System.out.println(username);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails user=userDetailsService.loadUserByUsername(username);
				boolean isValid=jwtUtil.validateToken(token, user.getUsername());
				if(isValid) {
					UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		else {
			System.out.println("this filter ignored when token is null");
		}
		filterChain.doFilter(request, response);
	}

}