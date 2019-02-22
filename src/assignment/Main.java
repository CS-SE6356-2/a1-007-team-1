package assignment;

public class Main {

    public static void main(String[] args) {
        new NamePrinter().printNames();
    }
}

class NamePrinter {
    class NamePrinter {
    /**
     * Prints the names of the group members separated by a semi-colon.
     *
     * Team note: if you change the separator, make sure to change it in the comments.
     */
    public void printNames() {
        String separator = ";";

        String[] names = {
                "Jacob E.",
                "Taha J.",
                "John P",
                "Jacob B.",
                "Name 5"};

        System.out.println(String.join(separator, names));
    }
}
