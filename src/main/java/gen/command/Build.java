/*
 -----------------------------------------------------------------------------------
 Cours       : Génie logiciel (GEN)
 Fichier     : Build
 Auteur(s)   : Berney Alec & Forestier Quentin & Melvyn Herzig
 Date        : 06.03.2021
 -----------------------------------------------------------------------------------
 */

package gen.command;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import org.apache.commons.io.FilenameUtils;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.io.FileUtils;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.Extension;
import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.image.attributes.ImageAttributesExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;

import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;


/**
 * Class implémentant la sous commande build.
 *
 * @author Berney Alec, Forestier Quentin, Herzig Melvyn
 */
@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer>
{
    @CommandLine.Parameters(index = "0", description = "path to build",
            defaultValue = "/")
    String path;


    private File root;
    private File buildRoot;

    private final LinkedList<Extension> extensions = new LinkedList<>();
    private Parser parser;
    private HtmlRenderer renderer;

    private Map<String, String> configs;

    private Template layout;
    private FileTemplateLoader loader;


    /**
     * Méthode pour l'appel de la commande build.
     */
    @Override
    public Integer call()
    {
        Path p = Paths.get(path);

        String pathStart = p.isAbsolute() ? "" : ".";
        root = new File(pathStart + path);

        buildFiles();

        return 0;
    }

    /**
     * Créer le dossier build et génère les fichiers HTML
     */
    private void buildFiles()
    {
        try
        {
            buildRoot = new File(root.getPath() + "/build");
            File tempalteDir = new File(buildRoot + "/template");

            if (buildRoot.exists())
            {
                FileUtils.cleanDirectory(buildRoot);
            }

            FileUtils.copyDirectory(root, buildRoot, new FileFilter()
            {
                @Override
                public boolean accept(File pathname)
                {
                    // Exclue le fichier .git surtout utile pour notre environnement de test.
                    return !pathname.getName().startsWith(".");
                }
            });

            if (tempalteDir.exists())
            {
                FileUtils.deleteDirectory(tempalteDir);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        extensions.add(AutolinkExtension.create());
        extensions.add(YamlFrontMatterExtension.create());
        extensions.add(TablesExtension.create());
        extensions.add(StrikethroughExtension.create());
        extensions.add(ImageAttributesExtension.create());
        extensions.add(InsExtension.create());
        extensions.add(TaskListItemsExtension.create());

        parser = Parser.builder().extensions(extensions).build();
        renderer = HtmlRenderer.builder().extensions(extensions).escapeHtml(true).build();

        // Lecture du fichier de configuration
        try
        {

            File config = new File(buildRoot.getPath() + "/config.yaml");

            YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();


            InputStreamReader in = new InputStreamReader(Files.newInputStream(Path.of(config.getPath())));
            var doc = parser.parseReader(in);
            doc.accept(visitor);

            Map<String, List<String>> tmp = visitor.getData();

            configs = new HashMap<>();

            for(Map.Entry mapentry : tmp.entrySet())
            {
                configs.put((String)mapentry.getKey(), listToString((List<String>)mapentry.getValue()));
            }

            in.close();
            config.delete();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // Lecture du fichier layout
        try
        {
            loader = new FileTemplateLoader(new File(root.getPath() + "/template/"));
            loader.setSuffix(".html");
            Handlebars handlebars = new Handlebars(loader);
            handlebars.setPrettyPrint(true);


            //handlebars.registerHelper("myHelper", new Helper)
            layout = handlebars.compile("layout");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (File f : FileUtils.listFiles(new File(buildRoot.getPath()), null, true))
        {
            createHTMLFile(f);
        }

    }

    /**
     * Transforme le contenu du markdown en balise html
     *
     * @param file Fichier markdown a transformer
     * @return String contenu html
     */
    private String mdToHtml(File file)
    {

        YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();


        if (file.isFile())
        {
            if (FilenameUtils.getExtension(file.getName()).equals("md"))
            {
                try
                {
                    InputStreamReader in = new InputStreamReader(Files.newInputStream(Path.of(file.getPath())));
                    var doc =
                            parser.parseReader(in);
                    doc.accept(visitor);

                    Map<String, List<String>> tmp = visitor.getData();

                    for(Map.Entry mapentry : tmp.entrySet())
                    {
                        configs.put((String)mapentry.getKey(), listToString((List<String>)mapentry.getValue()));
                    }

                    in.close();

                    return renderer.render(doc);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * Créer le fichier html correspondant au fichier markdown en paramètre
     *
     * @param file Fichier markdown a transformer
     */
    private void createHTMLFile(File file)
    {
        String basename = FilenameUtils.getBaseName(file.getPath());
        String htmlContent = mdToHtml(file);
        if (htmlContent == null)
        {
            return;
        }

        try
        {
            File htmlFile = new File(file.getParentFile() + "/" + basename + ".html");
            htmlFile.createNewFile();
            FileWriter writer = new FileWriter(htmlFile);

            configs.put("content", htmlContent);

            var context = Context.newBuilder(configs).resolver(MapValueResolver.INSTANCE).build();

            layout.apply(context, writer);

            //writer.write();
            writer.close();
            file.delete();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private String listToString(List<String> strings)
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(String s : strings)
        {
            if(first)
                first = false;
            else
                result.append(", ");
            result.append(s);
        }
        return result.toString();
    }

    /**
     * Génère les meta données à partir d'une map
     *
     * @param data Map contenant les clés/valeurs des méta données
     * @return String contenant toutes les balises <meta>
     */
    private String renderMetadata(Map<String, List<String>> data)
    {
        StringBuilder metadata = new StringBuilder();
        for (Map.Entry<String, List<String>> d : data.entrySet())
        {
            StringBuilder values = new StringBuilder();
            for (int i = 0; i < d.getValue().size(); ++i)
            {
                if (i != 0)
                {
                    values.append(", ");
                }

                values.append(d.getValue().get(i));
            }
            metadata.append(String.format("<meta name=\"%s\" content=\"%s\">\n", d.getKey(), values));
        }

        return metadata.toString();
    }
}