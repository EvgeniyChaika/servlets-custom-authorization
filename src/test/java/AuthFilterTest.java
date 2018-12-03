import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;

/**
 * Created by echaika on 03.12.2018
 */
public class AuthFilterTest {
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    private static final String path = "/view/login.jsp";

    @Test
    public void whenReturnIndexPage() {
        when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
    }
}
