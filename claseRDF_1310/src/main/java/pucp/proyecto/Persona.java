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
import org.apache.jena.vocabulary.RDFS;

/**
 *
 * @author HP
 */
public class Persona {

    static String NS = "https://www.codigopenalperu/#";
    static Model model = ModelFactory.createDefaultModel();

    public static void main(String[] args) {
        Model modelo = relaciones();
        descargarArchivo(modelo, "personas");
    }

    public static Model relaciones() {
        //String NS = "https://www.codigopenalperu/";
        //Model model = ModelFactory.createDefaultModel();

        Resource persona = crearRecurso(NS, "persona", model);

        //Propiedades
        Property nombreCompleto = crearPropiedad(NS, "nombreCompleto", model);
        Property edad = crearPropiedad(NS, "edad", model);
        Property region = crearPropiedad(NS, "region", model);
        Property provincia = crearPropiedad(NS, "provincia", model);
        Property distrito = crearPropiedad(NS, "distrito", model);
        Property direccion = crearPropiedad(NS, "direccion", model);
        Property genero = crearPropiedad(NS, "genero", model);
        Property etnicidad = crearPropiedad(NS, "etnicidad", model);

        persona.addProperty(nombreCompleto, "nombreCompleto");
        persona.addProperty(edad, "edad");
        persona.addProperty(region, "region");
        persona.addProperty(provincia, "provincia");
        persona.addProperty(distrito, "distrito");
        persona.addProperty(direccion, "direccion");
        persona.addProperty(genero, "genero");
        persona.addProperty(etnicidad, "etnicidad");

        //Definir Subclases
        defineSubClases(model, NS, persona);
        definePropiedades(model, NS);
        return model;
    }

    public static void defineSubClases(Model model, String NS, Resource Padre) {
        Resource victima = crearRecurso(NS, "Victima", model);
        Resource victimario = crearRecurso(NS, "Victimario", model);

        model.add(victima, RDFS.subClassOf, Padre);
        model.add(victimario, RDFS.subClassOf, Padre);

    }

    public static void definePropiedades(Model model, String NS) {
        Property Relacion = crearPropiedad(NS, "Relacion", model);

        //Nivel1
        Property EsConocidoDe = defineSubPropiedades("EsConocidoDe", Relacion);
        Property EsDesconocidoDe = defineSubPropiedades("EsDesconocidoDe", Relacion);

        //Nivel 2
        Property FamiliarDe = defineSubPropiedades("FamiliarDe", EsConocidoDe);
        Property NoFamiliarDe = defineSubPropiedades("NoFamiliarDe", EsConocidoDe);

        //Nivel 3 - Familiar
        Property EsPadreDe = defineSubPropiedades("EsPadreDe", FamiliarDe);
        Property EsMadreDe = defineSubPropiedades("EsMadreDe", FamiliarDe);
        Property EsEsposoDe = defineSubPropiedades("EsEsposoDe", FamiliarDe);
        Property EsEsposaDe = defineSubPropiedades("EsEsposaDe", FamiliarDe);
        Property EsHijoDe = defineSubPropiedades("EsHijoDe", FamiliarDe);
        Property EsPrimoDe = defineSubPropiedades("EsPrimoDe", FamiliarDe);
        Property EsNietoDe = defineSubPropiedades("EsNietoDe", FamiliarDe);
        Property EsAbueloDe = defineSubPropiedades("EsAbueloDe", FamiliarDe);

        //Nivel 3 - No Familiar
        Property EsAmigoDe = defineSubPropiedades("EsAmigoDe", NoFamiliarDe);
        Property EsVecinoDe = defineSubPropiedades("EsVecinoDe", NoFamiliarDe);

    }

    public static Property defineSubPropiedades(String NomSubProp, Property Prop) {
        Property subProp = crearPropiedad(NS, NomSubProp, model);
        model.add(subProp, RDFS.subPropertyOf, Prop);
        return subProp;
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
