package com.basaki.aspect;

import com.basaki.model.Param;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * {@code ParamAspectTest} is an unit test for {@code ParamAspect}.
 * <p>
 *
 * @author Indra Basak
 * @since 10/12/17
 */
public class ParamAspectTest {

    Param<String> stringProxy;

    Param<Boolean> booleanProxy;

    @Before
    public void setup() {
        Param<String> stringParam = new Param<>();
        ParamAspect aspect = new ParamAspect();
        AspectJProxyFactory factory = new AspectJProxyFactory(stringParam);
        factory.addAspect(aspect);
        stringProxy = factory.getProxy();

        Param<Boolean> booleanParam = new Param<>();
        ParamAspect aspect2 = new ParamAspect();
        AspectJProxyFactory factory2 = new AspectJProxyFactory(booleanParam);
        factory2.addAspect(aspect2);
        booleanProxy = factory.getProxy();
    }

    @Test
    public void testAspect() {
        stringProxy.execute("Hello there!");
        booleanProxy.execute(true);
    }
}
