package tt.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import tt.bean.SessionBean;


@Service
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private SessionBean sessionBean;

	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//HttpSession session = req.getSession();
        //User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       
        //System.out.println(authUser);
        
        handle( req, resp, auth);
        clearAuthenticationAttributes(req);
       
	}
	
	protected void handle( HttpServletRequest request, 
		      HttpServletResponse response, Authentication auth) throws IOException {
		
		        String targetUrl = determineTargetUrl(auth);
		 
		        if (response.isCommitted()) {
		            //logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
		            return;
		        }
		        
		        HttpSession session = request.getSession();
		        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		        
		        session.setAttribute("authUser", authUser);
		        
		        //sessionBean.getAuthUser();
		        //sessionBean.setAuthUser(authUser);
		 
		        redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	
	 /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {
        
    	boolean isUser = false;
        boolean isAdmin = false;
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
            else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
            	isUser = true;
                break;
            }
        }
 
        if (isAdmin) {
            return "/admin";
        } 
        else if(isUser) {
        	return "/";
        }
        else if (!isAdmin) {
            return "/eshop";
        } else {
            throw new IllegalStateException();
        }
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
