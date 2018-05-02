// package com.movieshop.servlets;
//
// import java.io.IOException;
//
// import javax.servlet.RequestDispatcher;
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.swing.text.ChangedCharSetException;
//
// import org.springframework.beans.factory.annotation.Autowired;
//
// import com.movieshop.model.User;
// import com.movieshop.model.UserDAO;
// import com.movieshop.model.UserException;
//
// @WebServlet("/reg")
// public class RegisterServlet extends HttpServlet {
// private static final long serialVersionUID = 1L;
//
// @Autowired // zashto ne raboti ?
// private UserDAO dao = new UserDAO();
//
// protected void doPost(HttpServletRequest request, HttpServletResponse
// response)
// throws ServletException, IOException {
//
// String name = request.getParameter("name");
// String lastName = request.getParameter("lastname");
// String email = request.getParameter("mail");
// String password = request.getParameter("password");
// String confirmPassword = request.getParameter("confirmPassword");
//
// User u = new User(name, lastName, email, password);
//
// try {
// if ((checkEMail(email)) && (checkName(name, lastName)) &&
// (checkPassword(password, confirmPassword))) {
// dao.register(u);
// }
// else {
// response.sendRedirect("./register");
// throw new UserException("Please enter a valid parameters!");
//
// }
//
// response.sendRedirect("./home");
//
// } catch (UserException e1) {
// response.getWriter().println("<h1> Something went wrong with the server! We
// are sorry! </h1>");
// e1.printStackTrace();
// }
//
// }
//
// private boolean checkEMail(String mail) {
// if ((mail.contains("@")) && (mail.contains("."))) {
// int indexQ = 0;
// int indexDot = 0;
// for (int i = 0; i < mail.length(); i++) {
// if (mail.charAt(i) == '@') {
// indexQ = i;
// }
// if (mail.charAt(i) == '.') {
// indexDot = i;
// }
// }
// if (indexQ < indexDot) {
// return true;
// } else {
// return false;
// }
// } else {
// return false;
// }
// }
//
// private boolean checkName(String name, String lastname) {
// if ((name.trim().length() >= 2) && (lastname.trim().length() >= 2)) {
// return true;
// }
// return false;
// }
//
// private boolean checkPassword(String password, String confirmPassword) {
// if ((password.length() >= 4) && (confirmPassword.equals(password))) {
// return true;
// }
// return false;
// }
// }
