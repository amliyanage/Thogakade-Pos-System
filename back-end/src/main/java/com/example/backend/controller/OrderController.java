package com.example.backend.controller;

import com.example.backend.bo.BOFactory;
import com.example.backend.bo.custom.OrderBo;
import com.example.backend.dto.OrderDto;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.backend.controller.ItemController.logger;

@WebServlet(urlPatterns = "/order", loadOnStartup = 1)
public class OrderController extends HttpServlet {

    OrderBo orderBO = (OrderBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Order);

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            OrderDto orderDTO = jsonb.fromJson(req.getReader(), OrderDto.class);
            System.out.println(orderDTO + "in order controller");
            writer.write(String.valueOf(orderBO.saveOrder(orderDTO)));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            resp.setContentType("application/json");
            jsonb.toJson(orderBO.getAllOrders(), writer);
            resp.setStatus(HttpServletResponse.SC_OK);
            logger.info("All Orders Retrieved Successfully");
        } catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}