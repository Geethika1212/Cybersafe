import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name, mobile) VALUES(?, ?)");

            ps.setString(1, name);
            ps.setString(2, mobile);

            int i = ps.executeUpdate();

            if(i > 0)
            {
                response.sendRedirect("practice.html");
            }
            else
            {
                response.getWriter().println("Failed to save user details.");
            }

            con.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}