package com.example.backend.controller;

import com.example.backend.bo.BOFactory;
import com.example.backend.bo.custom.ItemBo;
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
import java.util.List;
import java.util.logging.Logger;

@WebServlet( urlPatterns = "/item" )
public class ItemController extends HttpServlet {

    static Logger logger = Logger.getLogger(ItemController.class.getName());

    ItemBo itemBo = (ItemBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

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
            try {
                List<ItemDto> allItems = itemBo.getAllItems();
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(allItems, resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            resp.setContentType("application/json");
            Jsonb jsonb = JsonbBuilder.create();
            try {
                jsonb.toJson(itemBo.searchItem(id), resp.getWriter());
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

    // TODO : Delete Item
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        var writer = resp.getWriter();

        try{
            boolean isDeleted = itemBo.deleteItem(id);
            if (isDeleted){
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Item Deleted Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
