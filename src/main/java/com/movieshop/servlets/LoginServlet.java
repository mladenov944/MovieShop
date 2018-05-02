// package com.movieshop.servlets;
//
// import java.io.IOException;
//
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
//
// import com.movieshop.model.UserDAO;
//
// @WebServlet("/logins")
// public class LoginServlet extends HttpServlet {
// private static final long serialVersionUID = 1L;
//
// protected void doPost(HttpServletRequest request, HttpServletResponse
// response)
// throws ServletException, IOException {
// String email = request.getParameter("email");
// String password = request.getParameter("pass");
//
// UserDAO dao = new UserDAO();
// try {
// User user = dao.login(email, password);
//
// if (user.getId() != 0 && user.isAdmin() == false) {
//
// HttpSession session = request.getSession();
// session.setAttribute("cash", user.getMoney());
// session.setAttribute("id", user.getId());
// session.setAttribute("name", user.getName());
// session.setAttribute("lastName", user.getLastName());
// session.setMaxInactiveInterval(600);
//
// response.sendRedirect("./loggedIn");
// } else {
// HttpSession session = request.getSession();
//
// session.setAttribute("id", user.getId());
// session.setAttribute("name", user.getName());
// session.setAttribute("isAdmin", user.isAdmin());
// session.setAttribute("email", user.getEmail());
// session.setMaxInactiveInterval(6000);
// response.sendRedirect("./admin");
// }
//
// } catch (UserException e) {
// response.getWriter().println("<h1> You did not log in ! </h1>");
// response.sendRedirect("./login");
// e.printStackTrace();
// }
//
// }
//
// }
