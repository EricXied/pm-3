package servlet;

import dal.*;
import model.Characters;
import model.Player;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findcharacters")
public class FindCharacters extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // Message label for response messages
    private static final String RESPONSE_MESSAGE = "response";

    @Override
    public void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    public void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        // Map for storing messages
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.  This comes from the query string
        // (for GET queries) or the HTTP headers (for POST queries), but
        // the Servlet interface provides the same API for accessing the
        // value in both cases.
        String email = req.getParameter("email");
        if (email == null || email.trim().isEmpty()) {
            messages.put(RESPONSE_MESSAGE, "Please enter a valid email.");
        } else {
            // Retrieve the BlogUsers records and store as an attribute.
            try (Connection cxn = ConnectionManager.getConnection()) {
                Player player = PlayerDAO.getPlayerByEmail(cxn, email);

                req.setAttribute(
                        "Characters",
                        CharacterDAO.getCharactersByPlayerId(cxn, player)
                );
                messages.put(RESPONSE_MESSAGE, "Displaying results for " + email);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/FindCharacters.jsp").forward(req, resp);
    }
}
