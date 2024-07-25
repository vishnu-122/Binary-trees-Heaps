import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
        private ArrayList<T> list;

        public MaxHeap(){
            list = new ArrayList<>();
        }

        public void add(T item){
            list.add(item);
            int current = list.size() - 1;

            while(current > 0){
                int parent = (current -1)/2;
                if (list.get(parent).compareTo(list.get(current))<0){
                    T temp = list.get(current);
                    list.set(current,list.get(parent));
                    list.set(parent,temp);
                }else {
                    break;
                }
                current = parent;
            }
        }
}
