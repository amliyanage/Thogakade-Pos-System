package com.example.backend.controller;

import com.example.backend.bo.ItemBo;
import com.example.backend.bo.custom.ItemBoImpl;
import com.example.backend.dto.ItemDto;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet( urlPatterns = "/item" )
public class ItemController extends HttpServlet {

    ItemBo itemBo = new ItemBoImpl();

    // TODO : Add Item
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try ( var reader = req.getReader(); var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDto = jsonb.fromJson(reader, ItemDto.class);

            try{
                if (itemBo.addItem(itemDto)){
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                    writer.write("Item Added Successfully");
                }
                else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    writer.write("Failed to add Item");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO : Get Item
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");

        if (id.equals("all")) {

        } else {
            resp.setContentType("application/json");
            Jsonb jsonb = JsonbBuilder.create();
            try {
                jsonb.toJson(itemBo.searchItem(Integer.parseInt(id)), resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO : Update Item

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try ( var reader = req.getReader(); var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDto = jsonb.fromJson(reader, ItemDto.class);

            if (itemBo.updateItem(itemDto)){
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Item Updated Successfully");
            }
            else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                writer.write("Failed to update Item");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
