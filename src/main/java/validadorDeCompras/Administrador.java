package validadorDeCompras;


import javax.persistence.Entity;

import criteriosCategorias.CriterioCategorizacion;

@Entity
public class Administrador extends Usuario {


	public void otorgarJerarquia(CriterioCategorizacion padre, CriterioCategorizacion hijo) {
	padre.getCriterioHijo().add(hijo);
	hijo.setCriterioPadre(padre);	
        }

}
