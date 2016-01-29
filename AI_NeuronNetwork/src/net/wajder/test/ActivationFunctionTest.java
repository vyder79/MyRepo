package net.wajder.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.wajder.network.ActivFuncEnum;
import net.wajder.network.ActivationFunction;

/**
 * Klasa do testów jednostkowych dla funkcji aktywacji
 * 
 * @author vyder
 *
 */
public class ActivationFunctionTest {
	
	private ActivationFunction af;
	private ActivationFunction af2;
	
	@Before
	public void setUp() {
		this.af = new ActivationFunction();
		this.af2 = new ActivationFunction(ActivFuncEnum.SIGMOID);
	}

	@Test
	public void testActivationFunction() {
		Assert.assertEquals(ActivFuncEnum.LINEAR, af.getActivFunc());
		Assert.assertEquals(ActivFuncEnum.SIGMOID, af2.getActivFunc());
		Assert.assertNotEquals(ActivFuncEnum.SIGMOID, af.getActivFunc());
	}

	@Test
	public void testActivate() {
		Assert.assertNotNull(af.activate(1));
		Assert.assertEquals(1d, af.activate(1), 0.001);
		Assert.assertNotNull(af2.activate(1));
	}


}
