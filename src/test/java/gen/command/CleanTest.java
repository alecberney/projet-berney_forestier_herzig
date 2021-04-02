/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Statique
 Auteur(s)   : Berney Alec & Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen.command;

import gen.Statique;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la commande clean
 *
 * @author Berney Alec
 */
public class CleanTest
{
    private static final String path = "/test";

    /**
     * Test si la commande clean retourne bien une erreur si le path n'existe pas.
     */
    @Test
    void runTestNonExistantPath()
    {
        // Préparation de la commande.
        Callable<Integer> callable = new Statique();
        CommandLine cmd = new CommandLine(callable);

        // Arguments.
        String[] args = new String[]{"clean", "blabla"};

        // Ne fait rien -> impossible à vérifier
        cmd.execute(args);

        // A tester si on décide de jeter une exception
        /*try
        {
            Assertions.assertThrows(IOException.class, () -> {
                cmd.execute(args);
            });
        }
        catch (Exception e)
        {

        }*/
    }

    /**
     * Test si la commande clean peut supprimer le dossier et ses fichiers sans chemin.
     */
    @Test
    void runTestEmptyPath()
    {
        // Préparation de la commande.
        Callable<Integer> callable = new Statique();
        CommandLine cmd = new CommandLine(callable);

        // Arguments.
        String[] args = new String[]{"clean"};

        // Création des fichiers à supprimer
        File folder = new File("./build");
        File underFolder  = new File("./build/images");
        File index  = new File("./build/index.html");
        File image  = new File("./build/images/test.png");

        // Vérifie si les dossiers et fichiers de tests existent
        if(folder.exists()) folder.delete();
        if(underFolder.exists()) underFolder.delete();
        if(index.exists()) index.delete();
        if(image.exists()) image.delete();

        // Création des dossiers
        folder.mkdirs();
        underFolder.mkdirs();

        // Création des fichiers
        try
        {
            index.createNewFile();
            image.createNewFile();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        // Exécution
        cmd.execute(args);

        // Tous les fichiers et dossiers sont bien supprimés?
        assertFalse(folder.exists());
        assertFalse(underFolder.exists());
        assertFalse(index.exists());
        assertFalse(image.exists());
    }

    /**
     * Test si la commande clean peut supprimer le dossier et ses fichiers correctement avec un chemin
     */
    @Test
    void runTestWithPath()
    {
        // Préparation de la commande.
        Callable<Integer> callable = new Statique();
        CommandLine cmd = new CommandLine(callable);

        // Arguments.
        String[] args = new String[]{"clean", path};

        // Création des fichiers à supprimer
        File folder = new File("." + path + "/build");
        File underFolder  = new File("." + path + "/build/images");
        File index  = new File("." + path + "/build/index.html");
        File image  = new File("." + path + "/build/images/test.png");

        // Vérifie si les dossiers et fichiers de tests existent
        if(folder.exists()) folder.delete();
        if(underFolder.exists()) underFolder.delete();
        if(index.exists()) index.delete();
        if(image.exists()) image.delete();

        // Création des dossiers
        folder.mkdirs();
        underFolder.mkdirs();

        // Création des fichiers
        try
        {
            index.createNewFile();
            image.createNewFile();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        // Exécution
        cmd.execute(args);

        // Tous les fichiers et dossiers sont bien supprimés?
        assertFalse(folder.exists());
        assertFalse(underFolder.exists());
        assertFalse(index.exists());
        assertFalse(image.exists());

    }

    /**
     * Supprimer le dossier de test à la fin des tests
     */
    @AfterAll
    static void clean()
    {
        File folder = new File("." + path);

        if(folder.exists())
        {
            folder.delete();
        }
    }
}
