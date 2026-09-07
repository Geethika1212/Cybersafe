import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PracticeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String completed = request.getParameter("practiceCompleted");

        if (completed == null) {
            completed = "Yes";
        }

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE users SET practice_completed=? ORDER BY id DESC LIMIT 1");

            ps.setString(1, completed);

            int rows = ps.executeUpdate();

            System.out.println("Rows Updated = " + rows);

            ps.close();
            con.close();

            response.sendRedirect("dashboard.html");

        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.getWriter().println(e);
        }
    }
}