
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

        boolean practiceCompleted = completed == null
                || completed.equalsIgnoreCase("yes")
                || completed.equalsIgnoreCase("true");

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                throw new ServletException("Database connection failed.");
            }

            PreparedStatement ps = con.prepareStatement(
                "UPDATE users SET practice_completed = ? ORDER BY id DESC LIMIT 1"
            );

            ps.setBoolean(1, practiceCompleted);

            int rows = ps.executeUpdate();

            System.out.println("Rows Updated = " + rows);

            ps.close();

            response.sendRedirect("dashboard.html");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/plain");
            response.getWriter().println(
                "Practice update failed: " + e.getMessage()
            );
        }
    }
}
