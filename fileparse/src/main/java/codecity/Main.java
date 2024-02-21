package codecity;
public class Main{
    public static void main(String[] args) {
        String fileString = "./testClass.java";
        LocalVarFinder lvf = new LocalVarFinder(fileString);
        System.out.println(lvf.toString());
    }
}