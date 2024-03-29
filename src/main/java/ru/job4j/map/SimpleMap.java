package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        boolean rsl = false;
        int index = indexFor((hash((key == null) ? 0 : key.hashCode())));
        if (table[index] == null) {
            table[index] = new MapEntry<K, V>(key, value);
            modCount++;
            count++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] rsl = new MapEntry[capacity * 2];
        capacity = rsl.length;
        for (MapEntry<K, V> entry : table) {
            if (entry == null) {
                continue;
            }
            int index = indexFor(hash((entry.key == null) ? 0 : entry.key.hashCode()));
            rsl[index] = entry;
        }
        table = rsl;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFor(hash((key == null) ? 0 : key.hashCode()));
        if (table[index] != null && Objects.equals(table[index].key, key)) {
            rsl = (V) table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int hashCode = (key == null) ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] != null && Objects.equals(table[index].key, key)) {
            table[index] = null;
            modCount++;
            count--;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int uniqueModCount = modCount;
            private int cell = 0;

            @Override
            public boolean hasNext() {
                if (uniqueModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cell < capacity && table[cell] == null) {
                    cell++;
                }
                return cell < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No elements");
                }
                return (K) table[cell++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}