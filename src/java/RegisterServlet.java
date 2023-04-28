import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String r = """
                   <!DOCTYPE html>
                   <html>
                     <head>
                         <title>Register</title>
                     </head>
                     <body>
                         <p>Registration successful.</p>
                     </body>
                   </html>
                   """;
        
        String db = "jdbc:mariadb://localhost/aurora";
        String reqName = request.getParameter("name");
        String reqPass = request.getParameter("pass");
        String reqMail = request.getParameter("mail");
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn  = DriverManager.getConnection(db);
            Statement stmt = conn.createStatement();
            String query = """
                    INSERT INTO potrebiteli(name, pass, mail)
                    VALUES (\"%s\", \"%s\", \"%s\");
                           """.formatted(reqName, reqPass, reqMail);
            ResultSet rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.getWriter().println(r);
    }
}
