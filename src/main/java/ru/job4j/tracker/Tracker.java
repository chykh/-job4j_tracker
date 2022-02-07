package ru.job4j.tracker;
import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index == -1 ? null : items[index];
    }

    public Item[] findAll() {
       return Arrays.copyOf(items, size);
    }

    public Item[] findByName(String key) {
        Item[] result = new Item[size];
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (key.equals(items[i].getName())) {
                result[counter] = items[i];
                counter++;
            }
        }
        return Arrays.copyOf(result, counter);
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
    return rsl;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if ((index != -1) && (id <= 100) && (id > 0))  {
        item.setId(id);
        items[index] = item;
        return true;
        } else {
            return false;
        }
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        if ((index != -1) && (id <= 100) && (id > 0)) {
            System.arraycopy(items, index + 1, items, index, items.length - index - 1);
            items[items.length - 1] = null;
            size--;
            return true;
        } else {
            return false;
        }
    }
}