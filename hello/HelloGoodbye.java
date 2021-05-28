public class HelloGoodbye {
    public static void main(String[] args) {
        String name1 = args[0].toString();
        String name2 = args[1].toString();

        System.out.println("Hello " + name1 + " and " + name2 + ".");
        System.out.println("Good bye " + name2 + " and " + name1 + ".");
    }
}
