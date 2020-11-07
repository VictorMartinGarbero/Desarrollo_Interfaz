/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appagenda;

import entidades.Provincia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author VictorMartin
 */
public class ConsultaProvincias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf= 
                        Persistence.createEntityManagerFactory("AppAgendaPU"); 
        EntityManager em = emf.createEntityManager();
        
        //Lista completa de las provincias
        Query queryProvincias = em.createNamedQuery("Provincia.findAll");
        List<Provincia> listProvincias = queryProvincias.getResultList();
        for(Provincia provincia : listProvincias){ 
            System.out.println(provincia.getNombre()); }
        
        for(int i=0;i<listProvincias.size();i++){ 
            Provincia provincia=listProvincias.get(i); 
            System.out.println(provincia.getNombre()); }
        
        Query queryProvinciaCadiz = em.createNamedQuery("Provincia.findByNombre"); 
        queryProvinciaCadiz.setParameter("nombre", "Cádiz"); 
        List<Provincia> listProvinciasCadiz =queryProvinciaCadiz.getResultList(); 
        for(Provincia provinciaCadiz:listProvinciasCadiz){ 
            System.out.println(provinciaCadiz.getId()+":"); 
            System.out.println(provinciaCadiz.getNombre()); }
        
        //<T> T find(Class<T> entityClass, Object primaryKey)
        Provincia provinciaId2=em.find(Provincia.class,2); 
        if (provinciaId2 != null){ 
            System.out.print(provinciaId2.getId() + ":"); 
            System.out.println(provinciaId2.getNombre()); } 
        else { 
            System.out.println("No hay ninguna provincia con ID=2"); }
       
        Provincia provinciaId15 = em.find(Provincia.class, 15); 
        em.getTransaction().begin(); 
        if (provinciaId15 != null){ 
            em.remove(provinciaId15); }
        else{ 
            System.out.println("No hay ninguna provincia con ID=15"); } 
        em.getTransaction().commit();

        em.close(); 
        emf.close(); 

    }
    
}
