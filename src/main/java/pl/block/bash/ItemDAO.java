package pl.block.bash;

import java.util.List;

public interface ItemDAO extends GenericDAO<Item, Long> {

    List<Item> findAll(boolean withBids);

    List<Item> findByName(String name, boolean fuzzy);

    List<ItemBidSummary> findItemBidSummaries();

}