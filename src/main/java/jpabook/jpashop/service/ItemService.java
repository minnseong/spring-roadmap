package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto dto) {
        Book findItem = (Book) itemRepository.findOne(itemId);
        // findItem.change(price, name, stockQuantity) -> 의미 있는 메서드를 통해 변경해야 함.
        findItem.change(dto.getName(), dto.getPrice(), dto.getStockQuantity());

//        findItem.setName(dto.getName());
//        findItem.setPrice(dto.getPrice());
//        findItem.setStockQuantity(dto.getStockQuantity());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
