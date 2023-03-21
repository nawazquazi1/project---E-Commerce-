package org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.connection.DbConnection;
import org.dao.OrderDao;
import org.model.Cart;
import org.model.Order;
import org.model.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order-now")
public class orderNowControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatte = new SimpleDateFormat("yyy-MM-dd");
			Date date = new Date();
			user auth = (user) request.getSession().getAttribute("auth");
			if (auth != null) {
				String pId = request.getParameter("id");
				int pq = Integer.parseInt(request.getParameter("quantity"));
				if (pq <= 0) {
					pq = 1;
				}
				Order orderModel = new Order();

				orderModel.setId(Integer.parseInt(pId));
				orderModel.setuId(auth.getId());
				orderModel.setQuantity(pq);
				orderModel.setDate(formatte.format(date));
				

				OrderDao orderDao = new OrderDao(DbConnection.getConnection());
				boolean result = orderDao.insertOrder(orderModel);
				if (result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c : cart_list) {
							if (c.getId() == Integer.parseInt(pId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
//					response.getWriter().println(auth.getId()+"id");
					response.sendRedirect("orders.jsp");
				} else {
					out.println("order failed");
//					response.getWriter().println(auth.getId()+"id");
				}
			} else {
				response.sendRedirect("login.jsp");
//				response.getWriter().println(auth.getId()+"id");
			
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}