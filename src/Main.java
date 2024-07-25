//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MyBinaryTree<String> bt = new MyBinaryTree<>((p1,p2)->p1.compareTo(p2));
        bt.add("Hilary");
        bt.add("Benedict");
        bt.add("Nasim");
        bt.add("Jana");
        bt.add("Anika");
        bt.add("Brock");
        bt.add("Yardley");
        bt.add("Cleo");
        bt.print();
    }
}