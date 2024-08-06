package com.example.backend.bo.custom;

import com.example.backend.dao.DAOFactory;
import com.example.backend.dao.custom.ItemDao;
import com.example.backend.dto.ItemDto;
import com.example.backend.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {

    ItemDao itemDao = (ItemDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean addItem(ItemDto itemDto) throws SQLException {
        return itemDao.save(
                new Item(
                        itemDto.getId(),
                        itemDto.getName(),
                        itemDto.getQty(),
                        itemDto.getPrice()
                )
        );
    }

    @Override
    public ItemDto searchItem(String id) throws SQLException {
        Item itemDto = itemDao.getData(id);

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
        return itemDao.update(
                new Item(
                        itemDto.getId(),
                        itemDto.getName(),
                        itemDto.getQty(),
                        itemDto.getPrice()
                )
        );
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDao.delete(id);
    }

    @Override
    public List<ItemDto> getAllItems() throws SQLException {
        List<Item> itemList = itemDao.getAll();

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
