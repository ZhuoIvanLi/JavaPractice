package Amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * linux find command as an api ,the api willl support finding files that has given size requirements and a file with a certain format like
 *
 * 1. find all file >5mb
 * 2. find all xml
 *  Assume file class
 *  {
 *  getName()
 *  directorylistfile()
 *  getFile()
 *
 *  create a library flexible that is flexible
 *  Design clases,interfaces.
 *
 *
 * Ask for more info and define the core objects. Then drag a simple UML to clarify you points
 * In this case, we have File, FileCommand and Filter
 *
 */
public class FileFindCommand {
    class File {
        String name;
        int size;
        int type;
        boolean isDirectory;
        File[] children;
    }

    abstract class Filter {
        abstract boolean apply(File file);
    }

    class MinSizeFilter extends Filter {
        int minSize;

        public MinSizeFilter(int minSize) {
            this.minSize = minSize;
        }

        boolean apply(File file) {
            return file.size > minSize;
        }
    }

    class TypeFilter extends Filter {
        int type;

        public TypeFilter(int type) {
            this.type = type;
        }

        boolean apply(File file) {
            return file.type == type;
        }
    }

    public class NotADirectoryException extends Exception {
        public NotADirectoryException(String m) {
            super(m);
        }
    }

    class FindCommand {
        public List<File> findWithFilters(File directory, List<Filter> filters) throws NotADirectoryException{
            if (!directory.isDirectory) {
                throw new NotADirectoryException("Error! Not a Directory.");
            }

            List<File> output = new ArrayList<>();
            findWithFilters(directory, filters, output);
            return output;
        }

        private void findWithFilters(File directory, List<Filter> filters, List<File> output) {
            if (directory.children == null) {
                return;
            }

            for (File file : directory.children) {
                if (file.isDirectory) {
                    findWithFilters(file, filters, output);
                } else {
                    boolean selectFile = true;

                    // check if the file meets the requirements or not
                    for (Filter filter : filters) {
                        if (!filter.apply(file)) {
                            selectFile = false;
                            break;
                        }
                    }

                    if (selectFile) {
                        output.add(file);
                    }
                }
            }
        }
    }
}
