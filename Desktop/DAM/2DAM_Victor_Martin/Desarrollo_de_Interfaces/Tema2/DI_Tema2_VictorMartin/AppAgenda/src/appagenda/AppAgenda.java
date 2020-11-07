        /*
         * To change this license header, choose License Headers in Project Properties.
         * To change this template file, choose Tools | Templates
         * and open the template in the editor.
         */
        package appagenda;

        import entidades.Persona;
        import entidades.Provincia;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.util.List;
        import javafx.scene.control.TableView;
        import javafx.scene.layout.Pane;
        import javax.persistence.EntityManager;
        import javax.persistence.EntityManagerFactory;
        import javax.persistence.Persistence;
        import javax.persistence.Query;

        /**
         *
         * @author VictorMartin
         */
        public class AppAgenda {

            /**
             * @param args the command line arguments
             */
            public static void main(String[] args) {
                    EntityManagerFactory emf=
                        Persistence.createEntityManagerFactory("AppAgendaPU"); 
                    EntityManager em=emf.createEntityManager();

                    //Creación objetos
                    Provincia provinciaSevilla=new Provincia(); 
                    provinciaSevilla.setNombre("Sevilla");
                    em.getTransaction().begin(); 
                    em.persist(provinciaSevilla); 
                    em.getTransaction().commit();

                    Provincia provinciaCadiz=new Provincia(); 
                    provinciaCadiz.setNombre("Cádiz");
                    em.getTransaction().begin(); 
                    em.persist(provinciaCadiz); 
                    em.getTransaction().commit();
                    
                    
                    

                    Query queryProvincias = em.createNamedQuery("Provincia.findAll");
                    List<Provincia> listProvincias = queryProvincias.getResultList();



                    em.close();
                    emf.close();
                    try{
                        DriverManager.getConnection("jdbc:derby:BDAgenda;shutdown=true"); 
                        } catch (SQLException ex){
                    }
            }
            
            class PersonaDetalleViewController {

        void setRootAgendaView(Pane rootAgendaView) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        void setPersona(EntityManager entityManager, Persona personaSeleccionada, boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        void setTableViewPrevio(TableView<Persona> tableViewAgenda) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        void mostrarDatos() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
                }


        }
