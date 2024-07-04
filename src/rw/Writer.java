package rw;

import java.io.IOException;
import family.tree.FamilyTree;

public interface Writer {
    FamilyTree read(String path) throws IOException, ClassNotFoundException;
    void write(String path, FamilyTree tree) throws IOException ;
}
