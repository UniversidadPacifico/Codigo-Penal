package pucp.proyecto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class CodigoPenal {

    static String NS = "https://www.codigopenalperu/#";

    static Model model = ModelFactory.createDefaultModel();

    public static void main(String[] args) {
        relacionesPersona();
        ModeloCodigoPenal();

        descargarArchivo(model, "CodigoPenalFinal");
    }

    public static void ModeloCodigoPenal() {
       Resource delito = crearRecurso(NS,"Delito",model);
       Resource sansion = crearRecurso(NS,"Sansion",model);
       Resource tvida = crearRecurso(NS,"Titulo Vida Salud",model);
       Resource homicidio = crearRecurso(NS,"Homicidio",model);
       Resource hcalificado = crearRecurso(NS,"H.Calificado",model);
       Resource hculposo = crearRecurso(NS,"H.Culposo",model);
       Resource hsimple = crearRecurso(NS,"H.Simple",model);
       Resource infanticidio = crearRecurso(NS,"Infaticidio",model);
       Resource isuicidio = crearRecurso(NS,"Inst.Suicidio",model);
       Resource hpiadoso = crearRecurso(NS,"H.Piadoso",model);
       Resource hviolenta = crearRecurso(NS,"H.E.Violenta",model);
       Resource parricidio = crearRecurso(NS,"Parricidio",model);
       Resource feminicidio = crearRecurso(NS,"Feminicidio",model);
       Resource sicariato = crearRecurso(NS,"Sicariato",model);
       Resource csicariato = crearRecurso(NS,"Consp.Sicariato",model);
       Resource cvictima = crearRecurso(NS,"CondicionVictima",model);
       Resource victima = crearRecurso(NS,"Victima",model);
       
       model.add(tvida,RDFS.subClassOf,delito);
       model.add(homicidio,RDFS.subClassOf,tvida);
       model.add(hcalificado,RDFS.subClassOf,homicidio);
       model.add(hculposo,RDFS.subClassOf,homicidio);
       model.add(hsimple,RDFS.subClassOf, homicidio);
       model.add(infanticidio,RDFS.subClassOf,homicidio);
       model.add(hpiadoso,RDFS.subClassOf,homicidio);
       model.add(hviolenta,RDFS.subClassOf,homicidio);
       model.add(isuicidio,RDFS.subClassOf,homicidio);
        
       model.add(parricidio,RDF.type,hsimple);
       model.add(feminicidio,RDF.type,hcalificado);
       model.add(sicariato,RDF.type,hcalificado);
       model.add(csicariato,RDF.type,hcalificado);
       model.add(cvictima,RDF.type,hcalificado);

        // Porpiedades del delito
        Property PenaMinima = crearPropiedad(NS, "PenaMinima", model);
        Property PenaMaxima = crearPropiedad(NS, "PenaMaxima", model);
        Property PenaMinimaAgravante = crearPropiedad(NS, "PenaMinimaAgravante", model);

        //Pena por HOMICIDIO
        homicidio.addProperty(PenaMinima, "6 años");
        homicidio.addProperty(PenaMaxima, "20 años");
        //Pena por PARRICIDIO
        parricidio.addProperty(PenaMinima, "15 años");
        parricidio.addProperty(PenaMinimaAgravante, "25 años");

    }

    public static void relacionesPersona() {

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
    }

    //public static void defineSubClases(Model model, String NS, Resource Padre) {
        
   
	
		//Resource victima = crearRecurso(NS, "Victima", model);
        //Resource victimario = crearRecurso(NS, "Victimario", model);

        //model.add(victima, RDFS.subClassOf, Padre);ME PARECE QUE VICTIMA DE PROPIEDAD
        //model.add(victimario, RDFS.subClassOf, Padre);
		
		 Property esVictima = defineSubPropiedades(NS, "es_victima", model);
		 Property esVictimario = defineSubPropiedades(NS, "es_victimario", model);
       
       
       //RELACIONES
       
   

    //}

    public static void definePropiedades(Model model, String NS) {
        Property Relacion = crearPropiedad(NS, "Relacion", model);

        //Nivel1
        Property EsConocidoDe = defineSubPropiedades("EsConocidoDe", Relacion);
        Property EsDesconocidoDe = defineSubPropiedades("EsDesconocidoDe", Relacion);

        //Nivel 2
        Property FamiliarDe = defineSubPropiedades("FamiliarDe", EsConocidoDe);
        Property NoFamiliarDe = defineSubPropiedades("NoFamiliarDe", EsConocidoDe);

        //Nivel 3 - Familiar
        Property EsParienteGrado1 = defineSubPropiedades("EsPG1", FamiliarDe);
		Property EsParienteGrado2 = defineSubPropiedades("EsPG2", FamiliarDe);
		Property EsParienteGrado3 = defineSubPropiedades("EsPG3", FamiliarDe);
		Property EsParienteGrado4 = defineSubPropiedades("EsPG4", FamiliarDe);
		
        /**Property EsMadreDe = defineSubPropiedades("EsMadreDe", EsParienteGrado1);
        //Property EsEsposoDe = defineSubPropiedades("EsEsposoDe", EsParienteGrado1);
        Property EsEsposaDe = defineSubPropiedades("EsEsposaDe", EsParienteGrado1);
        Property EsHijoDe = defineSubPropiedades("EsHijoDe", EsParienteGrado1);
        Property EsPrimoDe = defineSubPropiedades("EsPrimoDe", EsParienteGrado2);
        Property EsNietoDe = defineSubPropiedades("EsNietoDe",EsParienteGrado2);
        Property EsAbueloDe = defineSubPropiedades("EsAbueloDe", EsParienteGrado2);*/

        //Nivel 3 - No Familiar
        Property EsAmigoDe = defineSubPropiedades("EsAmigoDe", NoFamiliarDe);
        Property EsVecinoDe = defineSubPropiedades("EsVecinoDe", NoFamiliarDe);

    }
	
	   model.add(esPG1,RDFS.subPropertyOf, esVictima);
       model.add(esPG1,RDFS.subPropertyOf, esVictimario);
       model.add(esPG2,RDFS.subPropertyOf, esVictima);
       model.add(esPG2,RDFS.subPropertyOf, esVictimario);
       
       model.add(hijo, esPG1 ,esVictimario);
	   model.add(padre,esPG1,esVictima)
       model.add(padre, esVictima, parricidio);

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
