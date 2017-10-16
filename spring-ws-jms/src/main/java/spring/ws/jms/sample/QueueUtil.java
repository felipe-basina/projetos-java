package spring.ws.jms.sample;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;

import weblogic.jms.common.JMSEditHelper;
import weblogic.jms.extensions.JMSModuleHelper;
import weblogic.management.runtime.JMSDestinationRuntimeMBean;
import weblogic.management.runtime.ServerRuntimeMBean;

/**
 * The Class QueueUtil.
 */
@Component
public class QueueUtil {

	/** The jms mbean map. */
	private static Map<String, JMSDestinationRuntimeMBean> jmsMbeanMap;

	/** The serverRuntimeMBean. */
	private static ServerRuntimeMBean srMBean;

	/** The jndi template. */
	@Autowired
	private JndiTemplate jndiTemplate;

	/**
	 * Verifica se exite consumidores para a fila.
	 *
	 * @param queueName
	 *            o nome da fila
	 * @return o numero de consumidores da fila
	 * @throws NamingException
	 */
	public boolean verifyConsumerExistsInQueue(final String queueName)
			throws NamingException {
		boolean exists = false;
		long total = 0;

		Context context = jndiTemplate.getContext();

		if (context != null) {
			try {

				JMSDestinationRuntimeMBean jmsMbean = getJMSDestinationRuntimeMBean(
						context, queueName);

				if (jmsMbean != null) {
					total = jmsMbean.getConsumersCurrentCount();
					if (total > 0) {
						exists = true;
					}
				}
			} finally {
				try {
					context.close();
				} catch (Exception e) {
					System.out.println("Erro ao fechar o contexto - "
							+ queueName);
				}
			}
		}

		return exists;
	}

	/**
	 * Recupera a quantidade de mensagens em uma fila.
	 *
	 * @param queueName
	 *            � o nome da fila
	 * @return o total de mensagens na fila
	 * @throws NamingException
	 *             the naming exception
	 */
	public long getMessagesCurrentCount(final String queueName)
			throws NamingException {
		long total = 0;

		Context context = jndiTemplate.getContext();

		try {
			JMSDestinationRuntimeMBean jmsMbean = getJMSDestinationRuntimeMBean(
					context, queueName);

			if (jmsMbean != null) {
				total = jmsMbean.getMessagesCurrentCount();
			}
		} finally {
			try {
				context.close();
			} catch (Exception e) {
				System.out.println("Erro ao fechar o contexto - " + queueName);
			}
		}
		return total;
	}

	/**
	 * Gets the jMS destination runtime m bean.
	 *
	 * @param context
	 *            the context
	 * @param queueName
	 *            the queue name
	 * @return the jMS destination runtime m bean
	 */
	private synchronized JMSDestinationRuntimeMBean getJMSDestinationRuntimeMBean(
			final Context context, final String queueName) {

		if (srMBean == null) {
			try {
				srMBean = JMSEditHelper.getRuntimeService(context)
						.getServerRuntime();
				ServerRuntimeMBean[] serverRuntimes = JMSEditHelper
						.getDomainRuntimeService(context).getServerRuntimes();
				boolean serverAvailable = false;
				for (int i = 0; i < serverRuntimes.length; i++) {
					if (serverRuntimes[i].getName().equals(srMBean.getName())) {
						serverAvailable = true;
						break;
					}
				}
				if (!serverAvailable) {
					srMBean = null;
				}
			} catch (Exception e) {
				System.out.println("Erro ao obter serverRuntimeMBean: "
						+ e.getMessage());
			}
		}

		if (srMBean != null) {
			if (jmsMbeanMap == null) {
				jmsMbeanMap = new ConcurrentHashMap<String, JMSDestinationRuntimeMBean>();
			}

			String serverQueueName = null;
			try {
				serverQueueName = queueName;
				if (!jmsMbeanMap.containsKey(serverQueueName)) {
					Queue queue = (Queue) context.lookup(serverQueueName);
					JMSDestinationRuntimeMBean jmsMbean = JMSModuleHelper
							.getJMSDestinationRuntimeMBean(context, queue);
					jmsMbeanMap.put(serverQueueName, jmsMbean);
				}
			} catch (JMSException e) {
				System.out.println("Erro ao obter informacoes da fila - "
						+ serverQueueName);
			} catch (NamingException e) {
				System.out.println("Erro ao identificar nome da fila - "
						+ serverQueueName);
			}
			return jmsMbeanMap.get(serverQueueName);
		}

		return null;
	}

	public void verificarSituacaoFila(String queueName) {

		try {

			final boolean hasConsumer = this
					.verifyConsumerExistsInQueue(queueName);

			final long totalCurrentMensages = this
					.getMessagesCurrentCount(queueName);

			System.out.println("\n ####### SITUACAO FILA :::: "
					.concat("\n..... fila tem consumidores? ")
					.concat(String.valueOf(hasConsumer))
					.concat("\n..... numero total de mensagens na fila: ")
					.concat(String.valueOf(totalCurrentMensages)));

		} catch (Exception ex) {
			System.out.println(" ## Erro ao verificar situacao fila: "
					.concat(ex.getMessage()));
		}
	}

}
