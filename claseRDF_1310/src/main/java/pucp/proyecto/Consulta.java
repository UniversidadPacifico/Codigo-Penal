/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pucp.proyecto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.util.FileManager;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Selector;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDF;

/**
 *
 * @author HP
 */
public class Consulta {

    static String NS = "https://www.codigopenalperu/#";
    static Model model = leerModelo();
    static InfModel inf = ModelFactory.createRDFSModel(model);

    public static void main(String[] args) {
        //leerModelo();
        //inf = ModelFactory.createRDFSModel(model);

        setDatos();

        consultar();
    }

    public static void setDatos() {

        String resourceURI = NS + "Pepe";
        Resource Pepe = model.createResource(resourceURI);

        resourceURI = NS + "PepeJr";
        Resource PepeJr = model.createResource(resourceURI);

        resourceURI = NS + "Victima";
        Resource Victima = model.getResource(resourceURI);
        model.add(Pepe, RDF.type, Victima);

        resourceURI = NS + "Victimario";
        Resource Victimario = model.getResource(resourceURI);
        model.add(PepeJr, RDF.type, Victimario);

        String propertyURI = NS + "EsPadreDe";
        Property EsPadreDe = model.getProperty(propertyURI);

        model.add(Pepe, EsPadreDe, PepeJr);
        
        //descargarArchivo(model, "PersonasEditado");
    }

    public static void consultar() {

        System.out.println("Consultando::");
        String resourceURI = NS + "Pepe";
        Resource Victima = model.createResource(resourceURI);

        resourceURI = NS + "PepeJr";
        Resource Victimario = model.createResource(resourceURI);

        String propertyURI = NS + "FamiliarDe";
        Property EsPadreDe = model.getProperty(propertyURI);

        Selector selector = new SimpleSelector(Victima, EsPadreDe, Victimario);
        //StmtIterator iter = model.listStatements(selector);
        StmtIterator iter = inf.listStatements(selector);

        while (iter.hasNext()) {
            System.out.println("******");
            System.out.println(iter.nextStatement().toString());
        }
    }

    public static Model leerModelo() {
        String inputFileName = "CodigoPenalFinal.rdf";
        Model modelo = FileManager.get().loadModel(inputFileName);
        return modelo;

    }

    private static void descargarArchivo(Model model, String nombreArchivo) {
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(nombreArchivo + ".rdf");
        } catch (FileNotFoundException e) {
            System.out.println("Ocurrio un error al crear el archivo.");
        }
        model.write(output, "RDF/XML-ABBREV");
    }
}
