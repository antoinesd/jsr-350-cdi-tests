package javax.state;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.state.spi.Provider;
import javax.state.spi.multiqualifiers.DurableStorageCapableLiteral;
import javax.state.spi.multiqualifiers.WhatEverCapableLiteral;

/**
 * @author Antoine Sabot-Durand
 */
@RunWith(Arquillian.class)
public class MultiQualifierWithClassTest {

    @Deployment
    public static Archive<?> createTestArchive() throws FileNotFoundException {

        WebArchive ret = ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addClasses(Capability.class, DurableStorageCapabiliity.class,
                        WhatEverCapabiliity.class,
                        Provider.class
                )
                .addPackage("javax.state.spi.multiqualifiers")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        return ret;
    }

    @Inject
    @Any
    Instance<Provider> providers;


    @Test
    public void shouldBeAmbigous() {
        Assert.assertTrue(providers.select(DurableStorageCapableLiteral.INSTANCE).isAmbiguous());
    }

    @Test
    public void shouldBeSatisfiedWithTwoQualifier() {
        Instance<Provider> p = providers.select(DurableStorageCapableLiteral.INSTANCE, WhatEverCapableLiteral.INSTANCE);
        Assert.assertTrue(!(p.isAmbiguous() || p.isUnsatisfied()));
    }

    @Test
    public void shouldBeSatisfiedWithOneQualifier() {
        Instance<Provider> p = providers.select(WhatEverCapableLiteral.INSTANCE);
        Assert.assertTrue(!(p.isAmbiguous() || p.isUnsatisfied()));
    }
}
