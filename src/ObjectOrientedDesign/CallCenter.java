package ObjectOrientedDesign;

import java.util.List;

/*
7.2 Call Center: three kinds of employee: respondent, manager, director. An incoming call must be first allocated to
    respondent who is free. If not, escalate the call to manager. If not, then escalate call to director. Implement a
    method dispatchCall() which assigns a call to the first available employee.
 */
public class CallCenter {
    private enum Rank{
        Responder, Director, Manager
    }

    public class CallHandler{
        // Initialize levels and number of levels
        private final int LEVELS = 3;
        private final int NUM_RESPONDENTS = 10;
        private final int NUM_MANAGERS = 4;
        private final int NUM_DIRECTORS = 2;

        // List of Employee: [0]: Respondents, [1]: Managers, [2]: Directors
        List<List<Employee>> employeeLevels;

        List<List<Call>> callQueues;

        public CallHandler(){...}

        // Get first employee who can handle the call
        public Employee getHandlerForCall(Call call){...}

        // Routes the call to an available employee or put it in callQueues
        public void dispatchCall(Caller caller){
            Call call = new Call(caller);
            dispatchCall(call);
        }

        public void dispatchCall(Call call){
            Employee e = getHandlerForCall(call);
            if(e!=null){
                e.receiveCall(call);
                call.setHandler(e);
            }else{
                call.reply("Please wait");
                callQueues.get(0).add(call);
            }
        }

        public boolean assignCall(Employee e){...}

    }

    // Represents a call from user
    public class Call{
        private Rank rank;
        // person who is calling
        private Caller caller;
        private Employee handler;

        public Call(Caller c){
            rank = Rank.Responder;
            caller = c;
        }

        // Set employee who is handling the call
        public void setHandler(Employee e){ handler = e; }
        public void reply(String message){...}
        public Rank getRank(){return rank; }
        public void setRank(Rank r){ this.rank = r}
        public Rank incrementRank(){...}
        public void disconnect(){...}

    }

    private class Employee{
        private Call currentCall = null;
        protected Rank rank;

        public Employee(){...}
        public Employee(CallHandler handler){...}

        // Start conversation
        public void receiveCall(Call call){...}

        // finish call
        public void callCompleted(){ ... }

        // Escalate the call and reassign
        public void escalateAndReassign(){...}

        // Assign new call to an employee, if he is free
        public void assignNewCall(){...}

        // Return whether or not the employee if free
        public boolean isFree(Call currentCall){ return currentCall == null; }

        public Rank getRank(){ return rank; }
    }

    public class Respondent extends Employee{
        public Respondent(){
            rank = Rank.Responder;
        }
    }

    public class Director extends Employee{
        public Director(){
            rank = Rank.Responder;
        }
    }

    public class Manager extends Employee{
        public Manager(){
            rank = Rank.Responder;
        }
    }
}
