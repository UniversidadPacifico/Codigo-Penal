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
import org.apache.jena.vocabulary.VCARD;

/**
 *
 * @author calva
 */
public class CodigoPenal_v4 {
 
    public static void main(String[] args){
        String NS = "https://www.codigopenalperu/#";
        Model model = ModelFactory.createDefaultModel();
        relaciones(model, NS);
        descargarArchivo(model, "CodigoPenalFinal_v4");
    }
    
    public static void relaciones(Model model, String NS){
        //Ley Peruana y Código penal
        Resource leyPeruana = crearRecurso(NS, "LeyPeruana", model);
        Property tiene = crearPropiedad(NS, "tiene", model);
        Resource codigoPenal = crearRecurso(NS, "CodigoPenal", model);
        crearRelacion(model, leyPeruana, tiene, codigoPenal);
        //Libros de código penal
        Property nombre = crearPropiedad(NS, "nombre", model);
        Resource libro1 = crearRecurso(NS, "Libro1", model);
        Resource libro2 = crearRecurso(NS, "Libro2", model);
        Resource libro3 = crearRecurso(NS, "Libro3", model);
        agregarPropiedadARecurso(libro1, nombre, "ParteGeneral");
        agregarPropiedadARecurso(libro2, nombre, "Delitos");
        agregarPropiedadARecurso(libro3, nombre, "Fallas");
        // Capítulo 1
        Resource capitulo1 = crearRecurso(NS, "Capitulo1", model);
        agregarPropiedadARecurso(capitulo1, nombre, "SaludYVida");
        crearRelacion(model, libro2, tiene, capitulo1);
        // Homicidio
        Resource homicidio = crearRecurso(NS, "Homicidio", model);
        crearRelacion(model, capitulo1, tiene, homicidio);
    }   
    
    
    public static void agregarPropiedadARecurso(Resource resource, Property property, String value){
        resource.addProperty(property, value);
    }
    
    public static void crearRelacion(Model model, Resource inputResource, Property property, Resource outputResource){
        model.add(inputResource, property, outputResource);
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
