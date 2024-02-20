package com.example.practice2.controller;

import com.example.practice2.model.Customer;
import com.example.practice2.model.Province;
import com.example.practice2.model.service.customer.CustomerServiceJDBC;
import com.example.practice2.model.service.customer.ICustomerService;
import com.example.practice2.model.service.province.IProvince;
import com.example.practice2.model.service.province.ProvinceJDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerController", urlPatterns = "/customers")
public class CustomerController extends HttpServlet {

static ICustomerService iCustomerService = new CustomerServiceJDBC();
static IProvince iProvince = new ProvinceJDBC();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("danh sách khách hàng");
        String action = req.getParameter("action");
        action = action==null ? "" : action;
        switch (action) {
            case "create" :
                showFormCustomer(req, resp);
            case "update" :
                showFormUpdate(req,resp);
                break;
            default:
                showAllCustomer(req, resp);

        }
    }

    private void showFormUpdate(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Province> province = iProvince.findAll();
        req.setAttribute("province",province);
        Customer customer =  iCustomerService.findById(id);
        req.setAttribute("customer",customer);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("edit.jsp");
        try {
            requestDispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showAllCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("customerlist.jsp");
        List<Customer> customerList = iCustomerService.findAll();
        // Gán lại dữ liệu cho view
        req.setAttribute("kh",customerList);
        requestDispatcher.forward(req, resp);
    }

    private static void showFormCustomer(HttpServletRequest req, HttpServletResponse resp){
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("create.jsp");
        List<Province> provinces = iProvince.findAll();
        req.setAttribute("province",provinces);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "create" :
                createNewCustomer(req,resp);
                break;
            case "find" :
                showFindByName(req,resp);
                break;
            case "update" :
                updateCustomer(req,resp);
        }
    }

    private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        Integer idProvince = Integer.valueOf(req.getParameter("idProvince"));
        String nameProvince = req.getParameter("nameProvince");
        Province province = new Province(idProvince,nameProvince);
        Customer customer = new Customer(name,email,address,province);
        iCustomerService.update(id,customer);
        try {
            resp.sendRedirect("/customers");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFindByName(HttpServletRequest req, HttpServletResponse resp) {
        // Lấy dữu liệu lên
        String name = req.getParameter("name");
        // Lấy ra list
        List<Customer> customerList = iCustomerService.findByName(name);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/customerlist.jsp");
        req.setAttribute("kh",customerList);
        try {
            requestDispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createNewCustomer(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        Integer idProvince = Integer.valueOf(req.getParameter("idProvince"));
        String nameProvince = req.getParameter("nameProvince");
        Province province = new Province(idProvince,nameProvince);
        Customer customer = new Customer(name,email,address,province);
        iCustomerService.save(customer);
        try {
            // trả về danh sách sau khi thêm
            resp.sendRedirect("/customers");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
