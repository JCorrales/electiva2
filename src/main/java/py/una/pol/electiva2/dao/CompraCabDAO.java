package py.una.pol.electiva2.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.una.pol.electiva2.domain.CompraCab;
import py.una.pol.electiva2.domain.CompraDet;
import py.una.pol.electiva2.domain.Proveedor;

@Named
@SessionScoped
public class CompraCabDAO extends BaseDAO<CompraCab> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8671782728635432659L;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public void comprar(CompraCab compraCab, List<CompraDet> selectedDetails, Proveedor proveedor) {

		try {
			userTransaction.begin();
			compraCab.setFecha(new Date());
			compraCab.setProveedor(proveedor);
			entityManager.persist(compraCab);
			LOGGER.info("insertando {} detalles", selectedDetails.size());
			for (CompraDet compraDet : selectedDetails) {
				compraDet.setCompraCab(compraCab);
				entityManager.persist(compraDet);
			}

			userTransaction.commit();

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
