import com.chaika.model.enums.Role;
import com.chaika.servlet.LogoutServlet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Created by echaika on 03.12.2018
 */
public class LogoutServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession httpSession;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenLogout() throws ServletException, IOException {
        when(request.getParameter("user")).thenReturn("Evgeniy");
        when(request.getParameter("password")).thenReturn("1");
        when(request.getParameter("rememberMe")).thenReturn(String.valueOf(Role.ADMIN));
        when(request.getSession()).thenReturn(httpSession);

        when(request.getRequestDispatcher("/")).thenReturn(requestDispatcher);

        LogoutServlet logoutServlet = new LogoutServlet();
        logoutServlet.doGet(request, response);

        verify(request).getSession();
        verify(request, times(1)).getSession();
        verify(response).sendRedirect("/");
        verify(response, times(1)).sendRedirect("/");
    }
}
