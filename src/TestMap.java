public class TestMap {
        public static void main(String[] args) {
            Map<Integer, String> map = new Map<>();

            Integer[] test_keys = {10, 7, 15, 5, 8, 12};
            String[] test_values = {"a", "b", "c", "d", "e", "f"};
            for (int i = 0; i < test_keys.length; i++) {
                map.insert(test_keys[i], test_values[i]);
            }
            map.printTree();

            map.insert(13, "g");
            map.printTree();

            Map<Integer, String> map2 = new Map<>(map);
            map2.delete(5);
            map2.delete(8);
            map2.delete(12);
            map2.insert(20, "h");
            map2.printTree();

            map2.insert(17, "i");
            map2.printTree();

            System.out.println(map2.isEmpty());
            map2.clear();
            System.out.println(map2.isEmpty());

            System.out.println(map.getValue(12));
            System.out.println(map.isExist(12));
            System.out.println(map.getValue(11));
            System.out.println(map.isExist(11));
            map.clear();

            Integer[] test2_keys = {10, 7, 15, 4, 9, 13, 20, 1, 6, 8, 17, 5};
            String[] test2_values = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};
            for (int i = 0; i < test2_keys.length; i++) {
                map.insert(test2_keys[i], test2_values[i]);
            }
            map.printTree();


            map.delete(13);
            map.printTree();

            Integer[] test3_keys = {10, 7, 17, 4, 9, 15, 20, 1, 6, 8, 19, 5};
            String[] test3_values = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};
            for (int i = 0; i < test3_keys.length; i++) {
                map2.insert(test3_keys[i], test3_values[i]);
            }
            map2.printTree();

            map2.delete(7);
            map2.printTree();

            System.out.println(map2.getValue(15));
            map2.setValue(15, "z");
            System.out.println(map2.getValue(15));
            map2.setValue(100, "v");
        }
    }
