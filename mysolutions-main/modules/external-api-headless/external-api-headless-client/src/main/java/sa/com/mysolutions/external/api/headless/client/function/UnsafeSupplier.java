package sa.com.mysolutions.external.api.headless.client.function;

import javax.annotation.Generated;

/**
 * @author ashish.singh
 * @generated
 */
@FunctionalInterface
@Generated("")
public interface UnsafeSupplier<T, E extends Throwable> {

	public T get() throws E;

}