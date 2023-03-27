package org.example;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.toFlatString());
        LinkedList<LinkedList<Integer>> list2 = new LinkedList<>();
        list2.add(list);
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(23423);
        list2.add(linkedList);
        list2.sortList();
        System.out.println(list2.toString());
        System.out.println(list2.size());
        System.out.println(list.compareTo(linkedList));
    }
}