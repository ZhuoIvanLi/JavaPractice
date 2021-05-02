package ObjectOrientedDesign;

/**
 * Solution: Construct strategy patter by using abstract class or interface. Declare the strategy pattern with
 * child class and use strategy pattern to control the child class.
 */
abstract class Locker {
    public abstract int lockerNumber(int n);
}

class BigLocker extends Locker {
    @Override
    public int lockerNumber(int n) {return n + 1;}
}

class SmallLocker extends Locker {
    @Override
    public int lockerNumber(int n) {return n + 2;}
}

// Create "setup locker" class for user to use
public class StrategyPattern {
    private Locker locker;

    public StrategyPattern(Locker locker) {
        this.locker = locker;
    }

    public int doSomething(int n) {
        return locker.lockerNumber(n);
    }
}

// Factory Method
class FactoryMethod {
    public FactoryMethod createMethod(String type) {
        if (type == "t"){
            // return new tMethod();
        } else if (type == "c") {
            // return new cMethod();
        }
        return null;
    }
}

// Example how to use this Strategy pattern
class WhenUse {

    public void useLocker() {
        StrategyPattern spBig = new StrategyPattern(new BigLocker());
        spBig.doSomething(1);
        StrategyPattern spSmall = new StrategyPattern(new SmallLocker());
        spSmall.doSomething(2);
    }
}


