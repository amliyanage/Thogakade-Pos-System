package com.example.backend.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.backend.bo.CustomerBo;
import com.example.backend.bo.custom.CustomerBoImpl;
import com.example.backend.dto.CustomerDto;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet( urlPatterns = "/customer" )
public class CustomerController extends HttpServlet {

    CustomerBo customerBo = new CustomerBoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try ( var reader = req.getReader(); var writer = resp.getWriter()) {

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDto customerDto = jsonb.fromJson(reader, CustomerDto.class);

           try {
               if (customerBo.addCustomer(customerDto)){
                     resp.setStatus(HttpServletResponse.SC_CREATED);
                     writer.write("Customer Added Successfully");
               }
               else {
                   resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                   writer.write("Failed to add Customer");
               }
           } catch (SQLException e) {
                e.printStackTrace();
           }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // TODO : Get Customer
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println(id);
            CustomerDto customerDto = customerBo.searchCustomer(id);
            System.out.println(customerDto);
            if (customerDto != null){
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(customerDto, resp.getWriter());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Todo : Update Customer
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try (var reader = req.getReader(); var writer = resp.getWriter()) {

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDto customerDto = jsonb.fromJson(reader, CustomerDto.class);

            if (customerBo.updateCustomer(customerDto)) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Customer Updated Successfully");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                writer.write("Failed to update Customer");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
