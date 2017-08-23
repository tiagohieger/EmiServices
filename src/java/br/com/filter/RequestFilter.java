package br.com.filter;

import br.com.api.io.socket.ServerManager;
import br.com.language.Messages;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"/api/*", ""})
public class RequestFilter implements Filter {

    public RequestFilter() {
        loadFreeMethods();
    }

    private final List<String> freeMethods = new ArrayList<>();

    private void loadFreeMethods() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
        final String requestURI = httpServletRequest.getRequestURI();

        for (String metodo : freeMethods) {
            String methodResquest = requestURI.substring(requestURI.lastIndexOf("/") + 1);
            if (methodResquest.equalsIgnoreCase(metodo)) {
                return;
            }
        }

        final Boolean isCanAccess = ServerManager.getInstance().isAuthorized(httpServletRequest);

        if (!isCanAccess) {
            final String msg = Messages.getInstance(null).getErrAccessDenied();
            throw new ServletException(msg);
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {

    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        //------------ Antes do envio da requisição ao robust.
        doBeforeProcessing(request, response);
        // processing the request
        chain.doFilter(request, response);
        // ---------- Depois do envio da requsição ao robust.
        doAfterProcessing(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
