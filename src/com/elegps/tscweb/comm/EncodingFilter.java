package com.elegps.tscweb.comm;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
/** *
 * @author  level
 * @version 1.0
 */
public class EncodingFilter extends HttpServlet
    implements Filter
{

	private static final long serialVersionUID = 1L;
	
    private FilterConfig filterConfig;
    private String targetEncoding;

    public EncodingFilter()
    {
        targetEncoding = "UTF-8";
    }

    public void init(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws ServletException
    {
        try
        {
            request.setCharacterEncoding(targetEncoding);
            response.setCharacterEncoding(targetEncoding);
            filterChain.doFilter(request, response);
        }
        catch(Exception sx)
        {
            filterConfig.getServletContext().log(sx.getMessage());
            sx.printStackTrace();
            throw new ServletException(sx);
        }
    }

    public void destroy()
    {
        filterConfig = null;
        targetEncoding = null;
    }
}
