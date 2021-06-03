package gen;

import java.io.File;

public interface Updatable
{
    void update(File fileUpdated);

    void beginNewUpdate();
}
