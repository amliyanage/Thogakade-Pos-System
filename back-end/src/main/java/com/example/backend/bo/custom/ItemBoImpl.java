package com.example.backend.bo.custom;

import com.example.backend.bo.ItemBo;
import com.example.backend.dao.ItemDao;
import com.example.backend.dao.custom.ItemDaoImpl;
import com.example.backend.dto.ItemDto;
import com.example.backend.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {

    ItemDao itemDao = new ItemDaoImpl();

    @Override
    public boolean addItem(ItemDto itemDto) throws SQLException {
        return itemDao.addItem(
                new Item(
                        itemDto.getId(),
                        itemDto.getName(),
                        itemDto.getQty(),
                        itemDto.getPrice()
                )
        );
    }

    @Override
    public ItemDto searchItem(int id) throws SQLException {
        ItemDto itemDto = itemDao.searchItem(id);

        if (itemDto != null) {
            return new ItemDto(
                    itemDto.getId(),
                    itemDto.getName(),
                    itemDto.getQty(),
                    itemDto.getPrice()
            );
        }
        return null;
    }

    @Override
    public boolean updateItem(ItemDto itemDto) throws SQLException {
        return itemDao.updateItem(
                new Item(
                        itemDto.getId(),
                        itemDto.getName(),
                        itemDto.getQty(),
                        itemDto.getPrice()
                )
        );
    }

    @Override
    public boolean deleteItem(int id) throws SQLException {
        return itemDao.deleteItem(id);
    }

    @Override
    public List<ItemDto> getAllItems() throws SQLException {
        List<Item> itemList = itemDao.getAllItems();

        List<ItemDto> itemDtoList = new ArrayList<>();

        for (Item item : itemList) {
            itemDtoList.add(
                    new ItemDto(
                            item.getId(),
                            item.getName(),
                            item.getQty(),
                            item.getPrice()
                    )
            );
        }

        return itemDtoList;
    }
}
