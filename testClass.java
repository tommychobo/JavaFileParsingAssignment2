public class testClass {
    private int notThisOne;
    private String notThisOneEither;

    public testClass(){
        notThisOne = 1;
        notThisOneEither = "Nope";
        int woah= 10;
        woah += 5;
        notThisOne = woah;
    }

    public double doSomethingCool(int v){
        float boop;
        boop = 5;
        v += boop;
        double aaah = 25.32;
        for(int doesItCatchThisOne = 0; doesItCatchThisOne < 10; doesItCatchThisOne++){
            boop--;
        }
        return boop*aaah;
    }
}
