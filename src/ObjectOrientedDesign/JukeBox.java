package ObjectOrientedDesign;

import java.util.List;
import java.util.Queue;

public class JukeBox {
    private CDPlayer cdPlayer;
    private User user;
    private List<CD> cdcollection;
    private SongSelector st;

    public JukeBox(CDPlayer cdPlayer, User user, List<CD> cdcollection, SongSelector st) {
        this.cdPlayer = cdPlayer;
        this.user = user;
        this.cdcollection = cdcollection;
        this.st = st;
    }

    public Song getCurrentSong(){ return st.getCurrentSong(); }
    public void setUser(User user){this.user = user;}

    public class CDPlayer{
        private Playlist p;
        private CD cd;

        public CDPlayer(Playlist p) {
            this.p = p;
        }

        public CDPlayer(CD cd) {
            this.cd = cd;
        }

        public CDPlayer(Playlist p, CD cd) {
            this.p = p;
            this.cd = cd;
        }

        public void playSong(Song s){...}

        public Playlist getP() {
            return p;
        }

        public void setP(Playlist p) {
            this.p = p;
        }

        public CD getCd(){ return cd; }
        public void setCd(CD cd){ this.cd = cd; }
    }

    public class Playlist{
        private Song song;
        private Queue<Song> songQueue;

        public Playlist(Song song, Queue<Song> songQueue) {
            this.song = song;
            this.songQueue = songQueue;
        }

        public Song getNextToPlay(){ return songQueue.peek();}
        public void addSong(Song song){ songQueue.add(song); }
    }

    public class CD{ ... }
    
    public class Song{
        public Song() {...}
    }

    public class User{
        private String name;
        private long id;

        // getter and setter for name and id;
        public User(String name, long id) { ... }
        public User getUser(){return this;}
        public static User addUser(String name, long id){  }
    }
}


