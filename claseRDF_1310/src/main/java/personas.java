/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class personas {
    
    public static void main(String[] args) {
        Model model = relaciones();
        descargarArchivo(model, "personas");
    }
    
    public static Model relaciones(){
        String NS = "https://www.codigopenalperu/";
        Model model = ModelFactory.createDefaultModel();
        
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
        
        Resource relacionFamiliar = crearRecurso(NS, "relacionFamiliar", model);
        Resource relacionNoFamiliar = crearRecurso(NS, "relacionNoFamiliar", model);
        
        // RELACION NO FAMILIAR
        
        Resource Conocido = crearRecurso(NS, "Conocido", model);
        Resource Desconocido = crearRecurso(NS, "Desconocido", model);
        
        model.add(Conocido, RDFS.subClassOf, relacionNoFamiliar);
        model.add(Desconocido, RDFS.subClassOf, relacionNoFamiliar);
        
        // Conocido
        Resource amigo = crearRecurso(NS, "amigo", model);
        Resource amiga = crearRecurso(NS, "amiga", model);
        Resource vecino = crearRecurso(NS, "vecina", model);
        Resource vecina = crearRecurso(NS, "vecino", model);
        Resource enamorado = crearRecurso(NS, "enamorado", model);
        Resource enamorada = crearRecurso(NS, "enamorada", model);
        
        
        model.add(amigo, RDF.type, Conocido);
        model.add(amiga, RDF.type, Conocido);
        model.add(vecino, RDF.type, Conocido);
        
        model.add(vecina, RDF.type, Conocido);
        model.add(enamorado, RDF.type, Conocido);
        model.add(enamorada, RDF.type, Conocido);
        
        
        //Desconocido
        
        Resource niño = crearRecurso(NS, "niño", model);
        Resource joven = crearRecurso(NS, "joven", model);
        Resource adulto = crearRecurso(NS, "adulto", model);
        Resource anciano = crearRecurso(NS, "anciano", model);
        
        model.add(niño, RDF.type, Desconocido);
        model.add(joven, RDF.type, Desconocido);
        model.add(adulto, RDF.type, Desconocido);
        model.add(anciano, RDF.type, Desconocido);
        
        // RELACION FAMILIAR
        
        model.add(relacionFamiliar, RDFS.subClassOf, persona);
        model.add(relacionNoFamiliar, RDFS.subClassOf, persona);
        // Grado de parentezco 
        Resource grado_1 = crearRecurso(NS, "PrimerGrado", model);
        Resource grado_2 = crearRecurso(NS, "SegundoGrado", model);
        Resource grado_3 = crearRecurso(NS, "TercerGrado", model);
        Resource grado_4 = crearRecurso(NS, "CuartoGrado", model);
        Resource grado_5 = crearRecurso(NS, "QuintoGrado", model);

        model.add(grado_1, RDFS.subClassOf, relacionFamiliar);
        model.add(grado_2, RDFS.subClassOf, relacionFamiliar);
        model.add(grado_3, RDFS.subClassOf, relacionFamiliar);
        model.add(grado_4, RDFS.subClassOf, relacionFamiliar);
        model.add(grado_5, RDFS.subClassOf, relacionFamiliar);
        
        //Primer grado
        Resource padre = crearRecurso(NS, "padre", model);
        Resource madre = crearRecurso(NS, "madre", model);
        Resource hijo = crearRecurso(NS, "hijo", model);
        Resource hija = crearRecurso(NS, "hija", model);
        Resource suegro = crearRecurso(NS, "suegro", model);
        Resource suegra = crearRecurso(NS, "suegra", model);
        Resource yerno = crearRecurso(NS, "yerno", model);
        Resource nuera = crearRecurso(NS, "nuera", model);
        Resource padrastro = crearRecurso(NS, "padrastro", model);
        Resource madrastra = crearRecurso(NS, "madrastra", model);
        Resource hijastro = crearRecurso(NS, "hijastro", model);
        Resource hijastra = crearRecurso(NS, "hijastra", model);
        
        model.add(padre, RDF.type, grado_1);
        model.add(madre, RDF.type, grado_1);
        model.add(hijo, RDF.type, grado_1);
        model.add(hija, RDF.type, grado_1);
        model.add(suegro, RDF.type, grado_1);
        model.add(suegra, RDF.type, grado_1);
        model.add(yerno, RDF.type, grado_1);
        model.add(nuera, RDF.type, grado_1);
        model.add(padrastro, RDF.type, grado_1);
        model.add(madrastra, RDF.type, grado_1);
        model.add(hijastro, RDF.type, grado_1);
        model.add(hijastra, RDF.type, grado_1);
        
        //Segundo grado
        Resource abuelo = crearRecurso(NS, "abuelo", model);
        Resource abuela = crearRecurso(NS, "abuela", model);
        Resource hermano = crearRecurso(NS, "hermano", model);
        Resource hermana = crearRecurso(NS, "hermana", model);
        Resource nieto = crearRecurso(NS, "nieto", model);
        Resource nieta = crearRecurso(NS, "nieta", model);
        
        Resource abueloConyuge = crearRecurso(NS, "abueloDelConyuge", model);
        Resource abuelaConyuge = crearRecurso(NS, "abuelaDelConyuge", model);
        Resource hermanastro = crearRecurso(NS, "hermanastro", model);
        Resource hermanastra = crearRecurso(NS, "hermanastra", model);
        Resource conyugeNieto = crearRecurso(NS, "conyugeDeNieto", model);
        Resource conyugeNieta = crearRecurso(NS, "conyugeDeNieta", model);
        Resource cuniado = crearRecurso(NS, "cuñado", model);
        Resource cuniada = crearRecurso(NS, "cuñada", model);
        Resource abuelastro = crearRecurso(NS, "abuelastro", model);
        Resource abuelastra = crearRecurso(NS, "abuelastra", model);
        Resource nietastro = crearRecurso(NS, "nietastro", model);
        Resource nietastra = crearRecurso(NS, "nietastra", model);
        
        model.add(abuelo, RDF.type, grado_2);
        model.add(abuela, RDF.type, grado_2);
        model.add(hermano, RDF.type, grado_2);
        model.add(hermana, RDF.type, grado_2);
        model.add(nieto, RDF.type, grado_2);
        model.add(nieta, RDF.type, grado_2);
        model.add(abueloConyuge, RDF.type, grado_2);
        model.add(abuelaConyuge, RDF.type, grado_2);
        model.add(hermanastro, RDF.type, grado_2);
        model.add(hermanastra, RDF.type, grado_2);
        model.add(conyugeNieto, RDF.type, grado_2);
        model.add(conyugeNieta, RDF.type, grado_2);
        model.add(cuniado, RDF.type, grado_2);
        model.add(cuniada, RDF.type, grado_2);
        model.add(abuelastro, RDF.type, grado_2);
        model.add(abuelastra, RDF.type, grado_2);
        model.add(nietastro, RDF.type, grado_2);
        model.add(nietastra, RDF.type, grado_2);
        
        //Tercer grado
        Resource bisabuelo = crearRecurso(NS, "bisabuelo", model);
        Resource bisabuela = crearRecurso(NS, "bisabuela", model);
        Resource bisnieto = crearRecurso(NS, "bisnieto", model);
        Resource bisnieta = crearRecurso(NS, "bisnieta", model);
        Resource tio = crearRecurso(NS, "tio", model);
        Resource tia = crearRecurso(NS, "tia", model);
        Resource sobrino = crearRecurso(NS, "sobrino", model);
        Resource sobrina = crearRecurso(NS, "sobrina", model);
        
        Resource bisabueloConyuge = crearRecurso(NS, "bisabueloDelConyuge", model);
        Resource bisabuelaConyuge = crearRecurso(NS, "bisabuelaDelConyuge", model);
        Resource tioConyuge = crearRecurso(NS, "tioDelConyuge", model);
        Resource tiaConyuge = crearRecurso(NS, "tiaDelConyuge", model);
        Resource conyugeBisNieto = crearRecurso(NS, "conyugeDeBisnieto", model);
        Resource conyugeBisNieta = crearRecurso(NS, "conyugeDeBisnieta", model);
        Resource sobrinoConyuge = crearRecurso(NS, "sobrinoDelConyuge", model);
        Resource sobrinaConyuge = crearRecurso(NS, "sobrinaDelConyuge", model);
        Resource conyugeTio = crearRecurso(NS, "conyugeDeTio", model);
        Resource conyugeTia = crearRecurso(NS, "conyugeDeTia", model);
        Resource conyugeSobrino = crearRecurso(NS, "conyugeDeSobrino", model);
        Resource conyugeSobrina = crearRecurso(NS, "conyugeDeSobrina", model);
        
        model.add(bisabuelo, RDF.type, grado_3);
        model.add(bisabuela, RDF.type, grado_3);
        model.add(bisnieto, RDF.type, grado_3);
        model.add(bisnieta, RDF.type, grado_3);
        model.add(tio, RDF.type, grado_3);
        model.add(tia, RDF.type, grado_3);
        model.add(sobrino, RDF.type, grado_3);
        model.add(sobrina, RDF.type, grado_3);
        model.add(bisabueloConyuge, RDF.type, grado_3);
        model.add(bisabuelaConyuge, RDF.type, grado_3);
        model.add(tioConyuge, RDF.type, grado_3);
        model.add(tiaConyuge, RDF.type, grado_3);
        model.add(conyugeBisNieto, RDF.type, grado_3);
        model.add(conyugeBisNieta, RDF.type, grado_3);
        model.add(sobrinoConyuge, RDF.type, grado_3);
        model.add(sobrinaConyuge, RDF.type, grado_3);
        model.add(conyugeTio, RDF.type, grado_3);
        model.add(conyugeTia, RDF.type, grado_3);
        model.add(conyugeSobrino, RDF.type, grado_3);
        model.add(conyugeSobrina, RDF.type, grado_3);
        
        //Cuarto grado
        Resource tatarabuelo = crearRecurso(NS, "tatarabuelo", model);
        Resource tatarabuela = crearRecurso(NS, "tatarabuela", model);
        Resource tataranieto = crearRecurso(NS, "tataranieto", model);
        Resource tataranieta = crearRecurso(NS, "tataranieta", model);
        Resource primo = crearRecurso(NS, "primo", model);
        Resource prima = crearRecurso(NS, "prima", model);
        Resource tioAbuelo = crearRecurso(NS, "tioAbuelo", model);
        Resource tiaAbuela = crearRecurso(NS, "tiaAbuela", model);
        Resource sobrinoNieto = crearRecurso(NS, "sobrinoNieto", model);
        Resource sobrinaNieta = crearRecurso(NS, "sobrinaNieta", model);
        
        Resource tatarabueloConyuge = crearRecurso(NS, "tatarabueloDelConyuge", model);
        Resource tatarabuelaConyuge = crearRecurso(NS, "tatarabuelaDelConyuge", model);
        Resource conyugeTataranieto = crearRecurso(NS, "conyugeDeTataranieto", model);
        Resource conyugeTataranieta = crearRecurso(NS, "conyugeDeTataranieta", model);
        Resource primoConyuge = crearRecurso(NS, "primoDelConyuge", model);
        Resource primaConyuge = crearRecurso(NS, "primaDelConyuge", model);
        Resource tioAbueloConyuge = crearRecurso(NS, "tioAbueloDelConyuge", model);
        Resource tiaAbuelaConyuge = crearRecurso(NS, "tiaAbuelaDelConyuge", model);
        Resource sobrinoNietoConyuge = crearRecurso(NS, "sobrinoNietoDelConyuge", model);
        Resource sobrinaNietaConyuge = crearRecurso(NS, "sobrinaNietaDelConyuge", model);
        Resource conyugePrimo = crearRecurso(NS, "conyugeDePrimo", model);
        Resource conyugePrima = crearRecurso(NS, "conyugeDePrima", model);
        Resource conyugeTioAbuelo = crearRecurso(NS, "conyugeDeTioAbuelo", model);
        Resource conyugeTiaAbuela = crearRecurso(NS, "conyugeDeTiaAbuela", model);
        Resource conyugeSobrinoNieto = crearRecurso(NS, "conyugeDeSobrinoNieto", model);
        Resource conyugeSobrinaNieta = crearRecurso(NS, "conyugeDeSobrinaNieta", model);
        
        model.add(tatarabuelo, RDF.type, grado_4);
        model.add(tatarabuela, RDF.type, grado_4);
        model.add(tataranieto, RDF.type, grado_4);
        model.add(tataranieta, RDF.type, grado_4);
        model.add(primo, RDF.type, grado_4);
        model.add(prima, RDF.type, grado_4);
        model.add(tioAbuelo, RDF.type, grado_4);
        model.add(tiaAbuela, RDF.type, grado_4);
        model.add(sobrinoNieto, RDF.type, grado_4);
        model.add(sobrinaNieta, RDF.type, grado_4);
        
        model.add(tatarabueloConyuge, RDF.type, grado_4);
        model.add(tatarabuelaConyuge, RDF.type, grado_4);
        model.add(conyugeTataranieto, RDF.type, grado_4);
        model.add(conyugeTataranieta, RDF.type, grado_4);
        model.add(primoConyuge, RDF.type, grado_4);
        model.add(primaConyuge, RDF.type, grado_4);
        model.add(tioAbueloConyuge, RDF.type, grado_4);
        model.add(tiaAbuelaConyuge, RDF.type, grado_4);
        model.add(sobrinoNietoConyuge, RDF.type, grado_4);
        model.add(sobrinaNietaConyuge, RDF.type, grado_4);
        model.add(conyugePrimo, RDF.type, grado_4);
        model.add(conyugePrima, RDF.type, grado_4);
        model.add(conyugeTioAbuelo, RDF.type, grado_4);
        model.add(conyugeTiaAbuela, RDF.type, grado_4);
        model.add(conyugeSobrinoNieto, RDF.type, grado_4);
        model.add(conyugeSobrinaNieta, RDF.type, grado_4);
        
        //Quinto grado
        Resource trastatarabuelo = crearRecurso(NS, "trastatarabuelo", model);
        Resource trastatarabuela = crearRecurso(NS, "trastatarabuela", model);
        Resource trastataranieto = crearRecurso(NS, "trastataranieto", model);
        Resource trastataranieta = crearRecurso(NS, "trastataranieta", model);
        Resource tioBisabuelo = crearRecurso(NS, "tioBisabuelo", model);
        Resource tiaBisabuela = crearRecurso(NS, "tiaBisabuela", model);
        Resource sobrinoBisnieto = crearRecurso(NS, "sobrinoBisnieto", model);
        Resource sobrinaBisnieta = crearRecurso(NS, "sobrinaBisnieta", model);
        Resource primoTio = crearRecurso(NS, "primoTio", model);
        Resource primaTia = crearRecurso(NS, "primaTia", model);
        Resource primoSobrino = crearRecurso(NS, "primoSobrino", model);
        Resource primaSobrina = crearRecurso(NS, "primaSobrina", model);
        
        model.add(trastatarabuelo, RDF.type, grado_5);
        model.add(trastatarabuela, RDF.type, grado_5);
        model.add(trastataranieto, RDF.type, grado_5);
        model.add(trastataranieta, RDF.type, grado_5);
        model.add(tioBisabuelo, RDF.type, grado_5);
        model.add(tiaBisabuela, RDF.type, grado_5);
        model.add(sobrinoBisnieto, RDF.type, grado_5);
        model.add(sobrinaBisnieta, RDF.type, grado_5);
        model.add(primoTio, RDF.type, grado_5);
        model.add(primaTia, RDF.type, grado_5);
        model.add(primoSobrino, RDF.type, grado_5);
        model.add(primaSobrina, RDF.type, grado_5);
        
        return model;
    }
    private static Property crearPropiedad(String NS, String id, Model model) {
        String propertyURI = NS + id;
        return model.createProperty(propertyURI); 
    }
    
    private static Resource crearRecurso(String NS, String id, Model model) {
        String resourceURI = NS + id;
        return model.createResource(resourceURI);
    }
    
    private static void descargarArchivo(Model model, String nombreArchivo){
        FileOutputStream output = null;
        try {
            output = new FileOutputStream (nombreArchivo + ".rdf");
        } catch(FileNotFoundException e){
            System.out.println("Ocurrio un error al crear el archivo.");
        }
        model.write(output,"RDF/XML-ABBREV");
    }
}
