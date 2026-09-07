import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String score = request.getParameter("score");
        String level = request.getParameter("level");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE users SET quiz_score=?, quiz_result=? ORDER BY id DESC LIMIT 1");

            ps.setInt(1, Integer.parseInt(score));
            ps.setString(2, level);

            int rows = ps.executeUpdate();

            System.out.println("Rows Updated = " + rows);

            ps.close();
            con.close();

            response.sendRedirect("result.html");

        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.getWriter().println(e);
        }
    }
}