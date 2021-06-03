package gen;

import java.io.File;

/**
 * Interface permettant de notifier lorsqu'une update doit être effectuée
 *
 * @author Forestier Quentin
 */
public interface Updatable
{
    /**
     * Effectue l'update selon le File reçu
     * @param fileUpdated File modifié
     */
    void update(File fileUpdated);

    /**
     * Permet de recommencer un bloc d'update
     */
    void beginNewUpdate();
}
