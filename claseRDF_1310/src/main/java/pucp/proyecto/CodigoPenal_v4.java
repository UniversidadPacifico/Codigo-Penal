/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pucp.proyecto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

/**
 *
 * @author calva
 */
public class CodigoPenal_v4 {
 
    public static void main(String[] args){
        String NS = "https://www.codigopenalperu/#";

        Model model = ModelFactory.createDefaultModel();

        descargarArchivo(model, "CodigoPenalFinal_v4");
    }
    
    public static void defineTipo(Model model, Resource childResource, Property parentResource) {
        model.add(childResource, RDF.type, parentResource);
    }
    
    public static void defineSubPropiedades(Model model, Property childProp, Property parentProp) {
        model.add(childProp, RDFS.subPropertyOf, parentProp);
    }
    
    private static Property crearPropiedad(String NS, String id, Model model) {
        String propertyURI = NS + id;
        return model.createProperty(propertyURI);
    }
    
    private static Resource crearRecurso(String NS, String id, Model model) {
        String resourceURI = NS + id;
        return model.createResource(resourceURI);
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
