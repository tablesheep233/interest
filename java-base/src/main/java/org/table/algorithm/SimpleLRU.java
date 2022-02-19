package org.table.algorithm;

import java.util.HashMap;
import java.util.Map;

public class SimpleLRU {

    private final int capacity;

    private final Map<String, Entry> cache;

    private final Entry head;

    private final Entry tail;

    public SimpleLRU(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.head = new Entry();
        this.tail = new Entry();
        head.next = tail;
        tail.pre = head;
    }

    public void put(String key, String value) {
        Entry entry = cache.get(key);
        if (entry != null) {
            entry.value = value;
            remove(entry);
        } else {
            if (cache.size() == capacity) {
                Entry die = tail.pre;
                remove(die);
                cache.remove(die.key);
            }
            entry = new Entry(key, value);
            cache.put(key, entry);
        }
        pre(entry);
    }

    public String get(String key) {
        Entry entry = cache.get(key);
        if (entry == null) {
            return null;
        }
        remove(entry);
        pre(entry);
        return entry.value;
    }

    private void pre(Entry entry) {
        entry.pre = head;
        entry.next = head.next;
        head.next.pre = entry;
        head.next = entry;
    }

    private void remove(Entry entry) {
        entry.pre.next = entry.next;
        entry.next.pre = entry.pre;
        entry.next = null;
        entry.pre = null;
    }

    public static class Entry {

        public Entry() {
        }

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private String key;

        private String value;

        private Entry pre;

        private Entry next;
    }

    public static void main(String[] args) {
        SimpleLRU lru = new SimpleLRU(2);
        lru.put("1", "1");
        lru.put("2", "2");
        System.out.println(lru.get("1"));
        lru.put("3", "3");
        System.out.println(lru.get("2"));
        lru.put("4", "4");
        System.out.println(lru.get("1"));
        System.out.println(lru.get("3"));
        System.out.println(lru.get("4"));
    }
}
