package Amazon;

import java.util.ArrayList;
import java.util.List;

// Ask for more info and define the core objects. Then drag a simple UML to clarify you points
// In this case, we have File, FileCommand and Filter
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

    class FindCommand {
        public List<File> findWithFilters(File directory, List<Filter> filters) {
            if (!directory.isDirectory) {
                //return new NotADirectoryException();
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
