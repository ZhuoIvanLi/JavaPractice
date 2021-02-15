package ObjectOrientedDesign;

import java.util.ArrayList;

public class FileSystem {
    public abstract class Entry {
        private int id;
        private String name;
        private Directory parent;
        private long created;
        private long lastUpdated;
        private long lastAccessed;

        public Entry(String n, Directory d){
            name = n;
            parent = d;
            created = System.currentTimeMillis();
            this.lastUpdated = System.currentTimeMillis();
            this.lastAccessed = System.currentTimeMillis();
        }

        public boolean delete(){
            if(parent == null) return false;
            return parent.deleteEntry(this);
        }

        public String getPath(){
            if(parent ==null) return name;
            return parent.getPath() + "/" + name;
        }

        public abstract int size();

        //Getters and Setters
    }

    public class Directory extends Entry {
        private ArrayList<Entry> contents;

        public Directory(String n, Directory d, int size) {
            super(n, d);
            this.contents = new ArrayList<Entry>();
        }

        public int size(){
            int size = 0;
            for(Entry content : contents){
                size+= content.size();
            }
            return size;
        }

        public int numberOfFiles() {
            int count = 0;
            for(Entry e : contents){
                if(e instanceof Directory){
                    count++;
                    Directory d = (Directory) e;
                    count += d.numberOfFiles();
                }else if (e instanceof File){
                    count++;
                }
            }

            return count;
        }

        public boolean deleteEntry(Entry entry){
            return contents.remove(entry);
        }

        public void addEntry(Entry entry){
            contents.add(entry);
        }

        protected ArrayList<Entry> getContents() { return contents; }
    }

    public class File extends Entry{
        private String content;
        private int size;

        public File(String n, Directory d, int size){
            super(n, d);
            this.size = size;
        }

        public int size(){
            return size;
        }
        //Getters and Setters
    }
}
