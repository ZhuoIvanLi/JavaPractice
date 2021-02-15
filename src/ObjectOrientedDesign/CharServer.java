package ObjectOrientedDesign;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CharServer {
    public class UserManager {
        private static UserManager instance;
        private HashMap<Integer, User> userById; // Map user id to user
        private HashMap<String, User> usersByAccountName; // Map account name to user
        private HashMap<Integer, User> onlineUsers; // Map from user id to online user

        public static UserManager getInstance(){
            if(instance==null){ instance= new UserManager; }
            return instance;
        }

        public void addUser(User fromUser, String toAccountName){}
        public void approveAddRequest(AddRequest req){}
        public void rejectAddRequest(AddRequest req){}
        public void userSignedOn(String accountName){}
        public void userSignedOff(String accountName){}
    }

    public class User {
        private int id;
        private String name;
        private UserStatus status = null;

        // Map other users id and chat
        private HashMap<Integer, PrivateChat> privateChat;
        // Map group chat
        private ArrayList<GroupChat> groupChat;
        private HashMap<Integer, AddRequest> receivedRequest;
        private HashMap<Integer, AddRequest> rejectedRequest;
        private HashMap<Integer, User> contacts;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public boolean sendMessageToUser(User u, String content){}
        public boolean sendMessageToGroup(int id, String content){}
        public void setStatus(UserStatus status){}
        public UserStatus getStatus() {
            return status;
        }
        public boolean addContact(User u){}
        public void receiveAddRequest(AddRequest req){}
        public void sendAddRequest(AddRequest req){}
        public void removeAddRequest(AddRequest req){}
        public void requestAddUser(String accountName){}
        public void addConversation(PrivateChat privateChat){}
        public void addConversation(GroupChat groupChat){}
        public int getId(){}
        public String getName(){}

    }

    public abstract class Conversation {
        private ArrayList<User> users;
        private int id;
        private ArrayList<Message> messages;

        public ArrayList<Message> getMessages(){}
        public boolean addMessage(Message m){}
        public int getId(){}
    }

    public class PrivateChat extends Conversation{
        public PrivateChat(User u1, User u2){}
        public User getAnotherUser(User u){}
    }

    public class GroupChat extends Conversation{
        public void removeParticipant(User user){}
        public void addParticipant(User user){}
    }

    public class Message {
        private String content;
        private Date date;

        public Message(String content, Date date) {
            this.content = content;
            this.date = date;
        }

        public String getContent() {
            return content;
        }

        public Date getDate() {
            return date;
        }
    }

    public class UserStatus {
        private String message;
        private UserStatusType type;

        public UserStatus(String message, UserStatusType type) {
            this.message = message;
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public UserStatusType getType() {
            return type;
        }
    }

    public class AddRequest {
        private User fromUser;
        private User toUser;
        private Date date;
        private RequestStatus status;

        public AddRequest(User fromUser, User toUser, Date date) {
            this.fromUser = fromUser;
            this.toUser = toUser;
            this.date = date;
        }

        public User getFromUser() {
            return fromUser;
        }

        public User getToUser() {
            return toUser;
        }

        public Date getDate() {
            return date;
        }

        public RequestStatus getStatus() {
            return status;
        }
    }

    public enum UserStatusType {
        Offline, Away, Idle, Available, busy
    }

    public enum RequestStatus {
        Unread, Read, Accepted, Rejected;
    }
}
