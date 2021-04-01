/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : command.BuildTest
 Auteur(s)   : Forestier Quentin & Melvyn Herzig
 Date        : 01.04.2021
 -----------------------------------------------------------------------------------
 */

package gen.command;

import gen.Statique;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class de test pour la commande build
 *
 * @author Forestier Quentin
 */
public class BuildTest
{
    private static final String path = "/buildtest";

    /**
     * Test si la commande build peut créer le dossier build ainsi que les fichiers html
     */
    @Test
    void runTestEmptyPath()
    {
        // Préparation de la commande.
        Callable<Integer> callable = new Statique();
        CommandLine cmd = new CommandLine(callable);

        String[] args = new String[]{"init"};

        cmd.execute(args);

        args = new String[]{"build"};

        cmd.execute(args);

        File index = new File("./build/index.html");

        assertTrue(index.exists());

    }

    /**
     * Test si la commande build peut créer le dossier build ainsi que les fichiers html avec un chemin spécifié
     */
    @Test
    void runTestWithPath()
    {
        // Préparation de la commande.
        Callable<Integer> callable = new Statique();
        CommandLine cmd = new CommandLine(callable);

        String[] args = new String[]{"init", path};

        cmd.execute(args);

        args = new String[]{"build", path};

        cmd.execute(args);

        File index = new File("./" + path + "/build/index.html");

        assertTrue(index.exists());
    }

    /**
     * Test si la conversion du markdown au html fonctionne
     * Toutes les traductions Markdown -> HTML ont été testée ici
     */
    @Test
    void runTestMarkdownToHtml()
    {
        // Préparation de la commande.
        Callable<Integer> callable = new Statique();
        CommandLine cmd = new CommandLine(callable);

        String[] args = new String[]{"init", path};

        cmd.execute(args);
        try
        {
            FileWriter writer = new FileWriter("./" + path + "/index.md", StandardCharsets.UTF_8);
            writer.write("---\n" +
                    "titre: Article de test\n" +
                    "auteur: Quentin Forestier\n" +
                    "date: 2021-03-25\n" +
                    "languages:\n" +
                    " - html\n" +
                    " - css\n" +
                    "---\n" +
                    "# Un titre\n" +
                    "## Un sous-titre\n" +
                    "Notre projet github [ici](https://github.com/gen-classroom/projet-berney_forestier_herzig) \n" +
                    "\n" +
                    "Une autre page de mon site [ici](./subdir/subdirfile.html)\n" +
                    "\n" +
                    "| col1 | col2 |\n" +
                    "|---|---|\n" +
                    "|cell1|cell2|\n" +
                    "\n" +
                    "~~du texte barré~~\n" +
                    "\n" +
                    "++du texte soulingé++\n" +
                    "\n" +
                    "Qu'est-ce qui est mieux ? \n" +
                    "- [ ]  Ne pas avoir des checkbox\n" +
                    "- [x] Avoir des checkbox\n" +
                    "\n" +
                    "\n" +
                    "---\n" +
                    "\n" +
                    "Une jolie image pour vos beaux yeux.\n" +
                    "\n" +
                    "![Une image](./images/image.png)\n" +
                    "\n" +
                    "\n" +
                    "La même image en plus petite\n" +
                    "\n" +
                    "![Une image plus petite](./images/image.png){width=200}");

            writer.close();

            args = new String[]{"build", path};
            cmd.execute(args);

            File file = new File("./" + path + "/build/index.html");
            String data = FileUtils.readFileToString(file, "UTF-8");

            String expectedOutput = "<!doctype html>\n" +
                    "<html>\n" +
                    "\t<head>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<meta name=\"titre\" content=\"Article de test\">\n" +
                    "<meta name=\"auteur\" content=\"Quentin Forestier\">\n" +
                    "<meta name=\"date\" content=\"2021-03-25\">\n" +
                    "<meta name=\"languages\" content=\"html, css\">\n" +
                    "\n" +
                    "\t</head>\n" +
                    "\t<body>\n" +
                    "<h1>Un titre</h1>\n" +
                    "<h2>Un sous-titre</h2>\n" +
                    "<p>Notre projet github <a href=\"https://github.com/gen-classroom/projet-berney_forestier_herzig\">ici</a></p>\n" +
                    "<p>Une autre page de mon site <a href=\"./subdir/subdirfile.html\">ici</a></p>\n" +
                    "<table>\n" +
                    "<thead>\n" +
                    "<tr>\n" +
                    "<th>col1</th>\n" +
                    "<th>col2</th>\n" +
                    "</tr>\n" +
                    "</thead>\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>cell1</td>\n" +
                    "<td>cell2</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<p><del>du texte barré</del></p>\n" +
                    "<p><ins>du texte soulingé</ins></p>\n" +
                    "<p>Qu'est-ce qui est mieux ?</p>\n" +
                    "<ul>\n" +
                    "<li><input type=\"checkbox\" disabled=\"\"> Ne pas avoir des checkbox</li>\n" +
                    "<li><input type=\"checkbox\" disabled=\"\" checked=\"\"> Avoir des checkbox</li>\n" +
                    "</ul>\n" +
                    "<hr />\n" +
                    "<p>Une jolie image pour vos beaux yeux.</p>\n" +
                    "<p><img src=\"./images/image.png\" alt=\"Une image\" /></p>\n" +
                    "<p>La même image en plus petite</p>\n" +
                    "<p><img src=\"./images/image.png\" alt=\"Une image plus petite\" width=\"200\" /></p>\n" +
                    "\n" +
                    "\t</body>\n" +
                    "</html>";

            assertEquals(expectedOutput, data.trim());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Supprimer les fichiers à la fin des tests
     */

    @AfterAll
    static void clean()
    {
        try
        {

            File index = new File("./index.md");
            File config = new File("./config.yaml");
            if (index.exists())
            {
                index.delete();
            }
            if (config.exists())
            {
                config.delete();
            }
            File dir = new File("./build");
            if (dir.exists())
            {
                FileUtils.deleteDirectory(dir);
            }

            // Nettoie après le test runTestWithPath

            dir = new File("./" + path);
            if (dir.exists())
            {
                FileUtils.deleteDirectory(dir);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
