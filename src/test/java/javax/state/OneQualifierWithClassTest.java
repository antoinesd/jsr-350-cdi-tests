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
import javax.state.spi.onequalifierwithclass.DurableStorageWhateverProviderOne;
import javax.state.spi.onequalifierwithclass.HasCapabilityLiteral;

/**
 * @author Antoine Sabot-Durand
 */
@RunWith(Arquillian.class)
public class OneQualifierWithClassTest {

    @Deployment
    public static Archive<?> createTestArchive() throws FileNotFoundException {

        WebArchive ret = ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addClasses(Capability.class, DurableStorageCapabiliity.class,
                        WhatEverCapabiliity.class,
                        Provider.class
                )
                .addPackage("javax.state.spi.onequalifierwithclass")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("javax.enterprise.inject.spi.Extension", "META-INF/services/javax.enterprise.inject.spi" +
                        ".Extension");

        return ret;
    }

    @Inject
    @Any
    Instance<Provider> providers;


    @Inject
    @Any 
    DurableStorageWhateverProviderOne provider; //Injection by final type @Any qualifier must be specified or @Default should be added in extension


    @Test
    public void providerShouldBePresent() {
        Instance<Provider> p = providers.select(new HasCapabilityLiteral(WhatEverCapabiliity.class));
        Assert.assertTrue(!p.isUnsatisfied());
    }

    @Test
    public void providerShouldBeUnique() {
        Instance<Provider> p = providers.select(new HasCapabilityLiteral(WhatEverCapabiliity.class), 
                new HasCapabilityLiteral(DurableStorageCapabiliity.class));
        Assert.assertTrue(!p.isAmbiguous());
    }


}
