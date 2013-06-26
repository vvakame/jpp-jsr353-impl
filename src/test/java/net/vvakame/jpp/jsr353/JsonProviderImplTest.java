package net.vvakame.jpp.jsr353;

import javax.json.spi.JsonProvider;

import net.vvakame.jpp.jsr353.JsonProviderImpl;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Test for {@link JsonProvider} implementation.
 * @author vvakame
 */
public class JsonProviderImplTest {

	protected Class<? extends JsonProvider> getProviderImplClass() {
		return JsonProviderImpl.class;
	}

	/**
	 * Test for check default provider.<br>
	 * Provider's class get from {@link #getProviderImplClass()}. 
	 * @author vvakame
	 */
	@Test
	public void setupServices() {
		JsonProvider provider = JsonProvider.provider();
		assertThat(provider, instanceOf(getProviderImplClass()));
	}
}
